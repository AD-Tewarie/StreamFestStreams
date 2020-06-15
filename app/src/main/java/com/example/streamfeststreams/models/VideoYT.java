package com.example.streamfeststreams.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT implements Parcelable {
    @SerializedName("id")
    @Expose
    private VideoID id;

    @SerializedName("snippet")
    @Expose
    private  SnippetYT snippet;

    public VideoYT() {
    }

    public VideoYT(VideoID id, SnippetYT snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    protected VideoYT(Parcel in) {
    }

    public static final Creator<VideoYT> CREATOR = new Creator<VideoYT>() {
        @Override
        public VideoYT createFromParcel(Parcel in) {
            return new VideoYT(in);
        }

        @Override
        public VideoYT[] newArray(int size) {
            return new VideoYT[size];
        }
    };

    public VideoID getId() {
        return id;
    }

    public void setId(VideoID id) {
        this.id = id;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
