package com.example.streamfeststreams.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelList {
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("items")
    @Expose
    private List<YTList> items;

    public ModelList() {
    }

    public ModelList(String nextPageToken, List<YTList> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<YTList> getItems() {
        return items;
    }

    public void setItems(List<YTList> items) {
        this.items = items;
    }
}


