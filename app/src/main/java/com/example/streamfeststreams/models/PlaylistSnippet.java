package com.example.streamfeststreams.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistSnippet {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnails")
    @Expose
    private ThumbnailYT thumbnails;

    @SerializedName("playlistId")
    @Expose
    private String playlistId;

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public PlaylistSnippet(String playlistId) {
        this.playlistId = playlistId;
    }

    public PlaylistSnippet() { }

    public PlaylistSnippet(String title, ThumbnailYT thumbnails) {
        this.title = title;
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ThumbnailYT getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ThumbnailYT thumbnails) {
        this.thumbnails = thumbnails;
    }
}
