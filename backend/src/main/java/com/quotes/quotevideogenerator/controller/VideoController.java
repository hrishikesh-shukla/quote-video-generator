package com.quotes.quotevideogenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/api/generateVideos")
    public String generateVideos() {
        try {
            return videoService.generateVideos();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating videos.";
        }
    }
}
