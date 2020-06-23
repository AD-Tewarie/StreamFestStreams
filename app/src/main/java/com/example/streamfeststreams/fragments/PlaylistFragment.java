package com.example.streamfeststreams.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.streamfeststreams.R;
import com.example.streamfeststreams.adapter.AdapterPlaylist;
import com.example.streamfeststreams.models.ModelPlaylist;
import com.example.streamfeststreams.models.PlaylistItems;
import com.example.streamfeststreams.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class PlaylistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private AdapterPlaylist adapter;
    private LinearLayoutManager manager;
    private FragmentManager f_manager;
    private List<PlaylistItems> videoList = new ArrayList<>();


    public PlaylistFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        // Inflate the layout for this fragment
        RecyclerView rv = view.findViewById(R.id.recycler_playlist_layout);
        adapter = new AdapterPlaylist(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);
        if(videoList.size() == 0){
            getJson();
        }
        return view;
    }

    private void getJson() {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.ply + YoutubeAPI.part_ply + YoutubeAPI.chid + YoutubeAPI.max + YoutubeAPI.KEY;
       // String playlistData = YoutubeAPI.BASE_URL + YoutubeAPI.ply + YoutubeAPI.part + YoutubeAPI.chid + YoutubeAPI.max + YoutubeAPI.KEY;
        Call<ModelPlaylist> data = YoutubeAPI.getPlaylistVideo().getYT(url);


        data.enqueue(new Callback<ModelPlaylist>() {
            @Override
            public void onResponse(Call<ModelPlaylist> call, Response<ModelPlaylist> response) {
                if (response.errorBody() != null) {
                    Log.w(TAG, "onResponse: " + response.errorBody() );
                }
                else {
                    ModelPlaylist mp = response.body();
                    videoList.addAll(mp.getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelPlaylist> call, Throwable t) {
                Log.e(TAG, "onFailure playlist: ", t );

            }
        });


    }
}