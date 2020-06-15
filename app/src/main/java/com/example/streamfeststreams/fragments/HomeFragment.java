package com.example.streamfeststreams.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.streamfeststreams.R;
import com.example.streamfeststreams.adapter.AdapterHome;
import com.example.streamfeststreams.models.ModelHome;
import com.example.streamfeststreams.models.VideoYT;
import com.example.streamfeststreams.network.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        RecyclerView rv = view.findViewById(R.id.recyclerView );
        adapter = new AdapterHome(getContext(), videoList );
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        if (videoList.size() == 0) {
            getJson();
        }

        return view;
    }



    private void getJson() {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.sch + YoutubeAPI.part + YoutubeAPI.chid + YoutubeAPI.max
                + YoutubeAPI.ord + YoutubeAPI.type + YoutubeAPI.KEY;
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if (response.errorBody() != null) {
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelHome mh = response.body();
                    videoList.addAll(mh.getItems());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t );

                }


        });
    }



}
