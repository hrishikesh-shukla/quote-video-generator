package com.quotes.quotevideogenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuoteVideoGeneratorApplication {

	public static void main(String[] args) {
//		String ffmpegPath = "C:\\Users\\shukla43\\Downloads\\ffmpeg\\bin"; // Update with your FFmpeg executable path
//		File ffmpegFile = new File(ffmpegPath);
//		if (ffmpegFile.exists()) {
//			System.out.println("FFmpeg executable found at: " + ffmpegFile.getAbsolutePath());
//		} else {
//			System.out.println("FFmpeg executable not found.");
//		}
		SpringApplication.run(QuoteVideoGeneratorApplication.class, args);
	}

}
