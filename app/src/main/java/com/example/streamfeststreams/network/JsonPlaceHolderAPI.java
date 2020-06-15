package com.example.streamfeststreams.network;

import com.example.streamfeststreams.fragments.LiveChatMessages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {
    @GET("youtube/v3/liveChat/messages?" +
            "liveChatId=Cg0KCzYtS2RSZ1VPME9BKicKGFVDTDdHcW8tM2NudUNqWXl6NzNmYmhHZxILNi1LZFJnVU8wT0E" +
            "&part=snippet" +
            "&key=AIzaSyB8pq2Ou4V34O_sEakj_3iaLNGwUgKixDo")
    Call<LiveChatMessages> getPosts();
}
