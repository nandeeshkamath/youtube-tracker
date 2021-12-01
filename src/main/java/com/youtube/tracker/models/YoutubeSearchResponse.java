package com.youtube.tracker.models;

import lombok.Value;

import java.time.OffsetDateTime;
import java.util.List;

@Value
public class YoutubeSearchResponse {
    String kind;
    String etag;
    String nextPageToken;
    String regionCode;
    PageInfo pageInfo;
    List<Item> items;

    @Value
    public static class Item {
        String kind;
        String etag;
        ID id;
        Snippet snippet;
    }

    @Value
    public static class ID {
        String kind;
        String videoID;
    }

    @Value
    public static class Snippet {
        OffsetDateTime publishedAt;
        String channelID;
        String title;
        String description;
        Thumbnails thumbnails;
        String channelTitle;
        String liveBroadcastContent;
        OffsetDateTime publishTime;
    }

    @Value
    public static class Thumbnails {
        Default thumbnailsDefault;
        Default medium;
        Default high;
    }

    @Value
    public static class Default {
        String url;
        long width;
        long height;
    }

    @Value
    public static class PageInfo {
        long totalResults;
        long resultsPerPage;
    }
}