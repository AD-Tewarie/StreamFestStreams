package com.example.streamfeststreams.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnippetList {

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnails")
    @Expose
    private ListThumb thumbnails;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("resourceId")
    @Expose
    private ResourceID resourceId;

    public SnippetList() {
    }

    public SnippetList(String publishedAt, String title, ListThumb thumbnails, ResourceID resourceId, String description) {
        this.publishedAt = publishedAt;
        this.title = title;
        this.thumbnails = thumbnails;
        this.resourceId = resourceId;
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ListThumb getThumbnails() {
        return thumbnails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnails(ListThumb thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ResourceID getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceID resourceId) {
        this.resourceId = resourceId;
    }
}
