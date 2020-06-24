package com.example.streamfeststreams.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListThumb {

    @SerializedName("medium")
    @Expose
    private ThumbMed medium;

    public ListThumb() {
    }

    public ThumbMed getMedium() {
        return medium;
    }

    public void setMedium(ThumbMed medium) {
        this.medium = medium;
    }

    public class ThumbMed {

        @SerializedName("url")
        @Expose
        private String url;

        public ThumbMed() {
        }

        public ThumbMed(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
