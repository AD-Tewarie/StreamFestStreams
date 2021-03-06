package com.example.streamfeststreams.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class SearchFragment extends Fragment {

    private EditText input_search;
    private Button search_btn;
    private AdapterHome adapter;
    private LinearLayoutManager manager;
    private List<VideoYT> videoList = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        input_search = view.findViewById(R.id.input_search);
        search_btn = view.findViewById(R.id.search_btn);
        RecyclerView rv = view.findViewById(R.id.recycler_search);

        adapter = new AdapterHome(getContext(), videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(input_search.getText().toString())){
                    getJson(input_search.getText().toString());

                }else {
                    Toast.makeText(getContext(), "Enter a video name", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    private void getJson(String search) {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.sch + YoutubeAPI.part + YoutubeAPI.chid + YoutubeAPI.max
                + YoutubeAPI.ord + YoutubeAPI.type + YoutubeAPI.query + search + YoutubeAPI.KEY;
        Call<ModelHome> data = YoutubeAPI.getHomeVideo().getYT(url);
        data.enqueue(new Callback<ModelHome>() {
            @Override
            public void onResponse(Call<ModelHome> call, Response<ModelHome> response) {
                if (response.errorBody() != null){
                    Log.w(TAG, "onResponse search :  " + response.errorBody().toString() );
                }else {
                    ModelHome mh = response.body();
                    videoList.clear();
                    if (mh.getItems().size() != 0){
                        videoList.addAll(mh.getItems());
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getContext(), "No video", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelHome> call, Throwable t) {
                Log.e(TAG, "onFailure search:  ", t );



            }
        });
    }


}
