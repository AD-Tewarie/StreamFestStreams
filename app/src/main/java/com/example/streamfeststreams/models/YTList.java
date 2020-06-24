package com.example.streamfeststreams.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YTList {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snippet")
    @Expose
    private SnippetList snippet;


    public YTList() {
    }

    public YTList(String id, SnippetList snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SnippetList getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetList snippet) {
        this.snippet = snippet;
    }
}
