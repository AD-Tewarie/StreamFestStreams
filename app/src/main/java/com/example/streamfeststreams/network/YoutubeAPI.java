package com.example.streamfeststreams.network;

import com.example.streamfeststreams.models.ModelHome;
import com.example.streamfeststreams.models.ModelPlaylist;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {

    // https://www.youtube.com/channel/UC8NnosPOvXnm0O1u5YnLQiw
    //https://www.googleapis.com/youtube/v3/
    // search?
    // part=snippet
    // &channelId=UC8NnosPOvXnm0O1u5YnLQiw
    // &eventType=live
    // &type=video
    //key 1 =  &key=AIzaSyDXMnoy7iTGTGSzy7PeyoCFEp5LyjwV45A
    // key 2 = AIzaSyB8pq2Ou4V34O_sEakj_3iaLNGwUgKixDo
    // AIzaSyC3GRGGR7pCAPHnpXY4YyYUpqrJiWs6FNk

    // test channel : UCX4sShAQf01LYjYQhG2ZgKg
    // streamfest id : UCcXcW-h_ST8xQi0j9C3wLjw
    //

    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String KEY = "&key=AIzaSyC3GRGGR7pCAPHnpXY4YyYUpqrJiWs6FNk";
    public static final String sch = "search?";
    public static final String max = "&maxResults=20";
    public static final String chid = "&channelId=UCL7Gqo-3cnuCjYyz73fbhGg";
    public static final String ord = "&eventType=upcoming";
    public static final String type = "&type=video";
    public static final String part = "part=snippet";

    public static final String ply = "playlists?";
    public static final String part_ply = "part=snippet";

    public static final String query = "&q=";


    // https://www.googleapis.com/youtube/v3/
    // playlists?
    // part=snippet
    // &channelId=UCJhjE7wbdYAae1G25m0tHAA
    // &maxResults=20
    // &key=[YOUR_API_KEY]


    public interface HomeVideo {
        @GET
        Call<ModelHome> getYT(@Url String url);

    }

    public interface PlaylistVideo {
        @GET
        Call<ModelPlaylist> getYT(@Url String url);
    }

    private static HomeVideo homeVideo = null;
    private static PlaylistVideo playlistVideo = null;

    public static HomeVideo getHomeVideo() {
        if (homeVideo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            homeVideo = retrofit.create(HomeVideo.class);
        }
        return homeVideo;
    }

    public static PlaylistVideo getPlaylistVideo() {
        if (playlistVideo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            playlistVideo = retrofit.create(PlaylistVideo.class);
        }
        return playlistVideo;
    }






}
