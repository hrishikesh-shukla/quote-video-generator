package com.quotes.quotevideogenerator.service;

import java.io.IOException;

public class VideoGenerator {

	public static void generateVideo(String photoPath, String musicPath, String quoteText, String outputVideoPath,
			String videoResolution) throws IOException, InterruptedException {
		// Example ffmpeg command to generate a video
		String ffmpegCommand = "ffmpeg -loop 1 -i " + photoPath + " -i " + musicPath + " -vf drawtext=text='"
				+ quoteText
				+ "':fontcolor=white:fontsize=24:x=(w-text_w)/2:y=(h-text_h)/2 -c:v libx264 -tune stillimage -c:a aac -strict experimental -b:a 192k -shortest -pix_fmt yuv420p -s "
				+ videoResolution + " " + outputVideoPath;

		// Execute the ffmpeg command
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", ffmpegCommand);
		Process process = processBuilder.start();
		process.waitFor();

		// Check if the video generation process was successful
		if (process.exitValue() == 0) {
			System.out.println("Video generated successfully.");
		} else {
			System.err.println("Error generating video.");
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		// Example usage of generateVideo method
		String photoPath = "path/to/photo.jpg";
		String musicPath = "path/to/music.mp3";
		String quoteText = "Your quote text here";
		String outputVideoPath = "path/to/output/video.mp4";
		String videoResolution = "1920x1080"; // YouTube video resolution

		generateVideo(photoPath, musicPath, quoteText, outputVideoPath, videoResolution);
	}
}
