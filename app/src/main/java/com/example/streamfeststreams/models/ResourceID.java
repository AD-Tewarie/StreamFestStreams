package com.example.streamfeststreams.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResourceID {

    @SerializedName("videoId")
    @Expose
    private String videoId;

    public ResourceID() {
    }

    public ResourceID(String videoId) {
        this.videoId = videoId;


    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
