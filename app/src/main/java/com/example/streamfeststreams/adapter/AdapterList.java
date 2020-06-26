package com.example.streamfeststreams.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.streamfeststreams.fragments.YoutubeView;
import com.example.streamfeststreams.models.YTList;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class AdapterList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<YTList> videoList;
    static boolean isPurple = true;

    public AdapterList(Context context, List<YTList> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    class YoutubeHolder extends RecyclerView.ViewHolder  {


        de.hdodenhof.circleimageview.CircleImageView thumbnail;
        TextView title, tgl;

        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.pl_image);
            title = itemView.findViewById(R.id.pl_title);
            tgl = itemView.findViewById(R.id.pl_tgl);
        }

        public void setData(YTList data) {
            final String getText = data.getSnippet().getTitle();
            String getTgl = data.getSnippet().getDescription();
            String getThumb = data.getSnippet().getThumbnails().getMedium().getUrl();
            final String VidID = data.getSnippet().getResourceId().getVideoId();


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, getText, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, YoutubeView.class);
                    i.putExtra("VidUrl", VidID );
                    context.startActivity(i);


                }

            });

            title.setText(getText);
            tgl.setText(getTgl);

            if (isPurple){
                thumbnail.setBorderColor(Color.parseColor("#FFA751"));
                isPurple = false;
            } else{
                thumbnail.setBorderColor(Color.parseColor("#8E54E9"));
                isPurple = true;
            }

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
        View view = inflater.inflate(R.layout.playlist_video, parent, false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        YTList videoYT = videoList.get(position);
        YoutubeHolder yth = (YoutubeHolder) holder;
        yth.setData(videoYT);


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}