package com.youtube.tracker.models;

public class YoutubeLink {
    private String link;

    public YoutubeLink(String videoId) {
        this.link = "https://youtu.be/".concat(videoId);
    }

    public String get() {
        return link;
    }
}
