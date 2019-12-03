package com.example.carsapi;

public class Youtube {

    private String videoUrl;

    public Youtube() {
    }

    public Youtube(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
