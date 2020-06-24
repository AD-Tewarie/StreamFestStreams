package com.example.streamfeststreams.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.streamfeststreams.R;
import com.example.streamfeststreams.adapter.AdapterList;
import com.example.streamfeststreams.models.ModelList;
import com.example.streamfeststreams.models.YTList;
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
public class ListFragment extends Fragment {

    private AdapterList adapter;
    private LinearLayoutManager manager;
    private List<YTList> videoList = new ArrayList<>();



    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pl_items, container, false);
        // Inflate the layout for this fragment
        RecyclerView rv = view.findViewById(R.id.recyclerViewPl );
        adapter = new AdapterList(getContext(), videoList );
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        if (videoList.size() == 0) {
            getJson();
        }

        if (getContext() != null && getActivity() != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            rv.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));
        }

        return view;
    }


   // base + ply + part + plchid + key
    private void getJson() {
        String videoID = getActivity().getIntent().getStringExtra("PlUrl");

        String url = YoutubeAPI.BASE_URL + YoutubeAPI.plyit + YoutubeAPI.part_ply + YoutubeAPI.max + YoutubeAPI.plchid+ videoID + "&" + YoutubeAPI.KEY;
        Call<ModelList> data = YoutubeAPI.getListVideo().getYT(url);
        data.enqueue(new Callback<ModelList>() {
            @Override
            public void onResponse(Call<ModelList> call, Response<ModelList> response) {
                if (response.errorBody() != null) {
                    Log.w(TAG, "onResponse: " + response.errorBody());
                } else {
                    ModelList ml = response.body();
                    videoList.addAll(ml.getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelList> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);

            }


        });
    }
}
