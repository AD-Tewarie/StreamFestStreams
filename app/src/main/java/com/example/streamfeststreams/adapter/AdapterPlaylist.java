package com.example.streamfeststreams.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streamfeststreams.R;
import com.example.streamfeststreams.models.PlaylistItems;
import com.example.streamfeststreams.models.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class AdapterPlaylist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<PlaylistItems> videoList;

    public AdapterPlaylist(Context context, List<PlaylistItems> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    class YoutubeHolder extends RecyclerView.ViewHolder  {

        ImageView thumbnail;
        TextView title, subtitle, info;


        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.playlist_thumb);
            title = itemView.findViewById(R.id.playlist_title);
            subtitle = itemView.findViewById(R.id.playlist_subtitle);
            info = itemView.findViewById(R.id.playlist_info);

        }

        public void setData(PlaylistItems data) {
            final String getText = data.getSnippet().getTitle();
            int getCount = videoList.size();
            String getThumb = data.getSnippet().getThumbnails().getMedium().getUrl();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, getText, Toast.LENGTH_SHORT).show();

                }

            });

            title.setText(getText);
            subtitle.setText(String.valueOf(getCount)+ " videos");
            info.setText(String.valueOf(getCount));
            Picasso.get()
                    .load(getThumb)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(thumbnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "Thumbnail loaded");

                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e(TAG, "Thumnail Error:", e );

                        }
                    });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item_playlist, parent, false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlaylistItems videoYT = videoList.get(position);
        YoutubeHolder yth = (YoutubeHolder) holder;
        yth.setData(videoYT);


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}

