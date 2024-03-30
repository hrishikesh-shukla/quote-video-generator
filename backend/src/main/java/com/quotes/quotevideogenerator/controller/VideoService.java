package com.quotes.quotevideogenerator.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class VideoService {

    // Specify the full path to the FFmpeg executable
    private static final String FFMPEG_PATH = "C:/Users/shukla43/Downloads/ffmpeg/bin/ffmpeg";

    public String generateVideos() {
        // Logic to fetch photo, quote, and music paths
        String photoPath = "https://source.unsplash.com/1600x900/?nature"; // Example photo path
        String musicPath = "https://demos.loopmasters.com/17791%2FZT_TM_DEMO.mp3"; // Example music path
        String[] quotes = { "Quote 1", "Quote 2", "Quote 3" }; // Example array of quotes

        // Generate output directory path based on current timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String outputDirectory = "src/main/resources/video/" + timestamp;
        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create threads for video generation tasks
        Thread youtubeThread = new Thread(() -> generateVideo(photoPath, musicPath, quotes[0], outputDirectory + "/youtube.mp4"));
        Thread shortVideoThread = new Thread(() -> generateVideo(photoPath, musicPath, quotes[1], outputDirectory + "/short.mp4"));

        // Start the threads
        youtubeThread.start();
        shortVideoThread.start();

        try {
            // Wait for both threads to complete
            youtubeThread.join();
            shortVideoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Videos generated successfully in directory: " + outputDirectory;
    }

    private void generateVideo(String photoPath, String musicPath, String quote, String outputVideoPath) {
        String ffmpegCommand = FFMPEG_PATH + " -i " + photoPath + " -i " + musicPath
                + " -vf \"drawtext=text='" + quote
                + "':fontfile=C\\:/Windows/Fonts/arial.ttf:fontsize=24:fontcolor=white:x=(w-text_w)/2:y=(h-text_h)/2\" -c:v libx264 -preset medium -tune stillimage -crf 18 -c:a aac -b:a 192k "
                + outputVideoPath;

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", ffmpegCommand);
            builder.redirectErrorStream(true); // Redirect error stream to output stream
            Process process = builder.start();

            // Capture output stream (optional)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("ffmpeg output: " + line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Video generation successful: " + outputVideoPath);
            } else {
                System.err.println("Error generating video: " + outputVideoPath);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Exception during video generation: " + e.getMessage());
        }
    }
}
