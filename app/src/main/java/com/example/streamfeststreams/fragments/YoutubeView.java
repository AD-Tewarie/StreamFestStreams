package com.example.streamfeststreams.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.streamfeststreams.R;
import com.example.streamfeststreams.models.VideoID;
import com.example.streamfeststreams.network.JsonPlaceHolderAPI;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeView extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderAPI jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderAPI.class);
    private Handler customHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);



        //repeates this method every second
      //  customHandler.postDelayed(updateTimerThread, 0);

        final com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView third_party_player_view = findViewById(R.id.third_party_player_view);

        //Youtube player
        third_party_player_view.getPlayerUiController().showFullscreenButton(true);
        third_party_player_view.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoID = getIntent().getStringExtra("VidUrl");
                youTubePlayer.loadVideo(videoID, 0);
            }
        });

        //makes the youtube player fullscreen
        third_party_player_view.getPlayerUiController().setFullScreenButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Window window = getWindow();
                ActionBar actionBar = getSupportActionBar();
                if (third_party_player_view.isFullScreen()) {
                    third_party_player_view.exitFullScreen();
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    // Show ActionBar

                    if (actionBar != null) {
                        actionBar.show();
                    }
                } else {
                    third_party_player_view.enterFullScreen();
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                    // Hide ActionBar
                    if (actionBar != null) {
                        actionBar.hide();
                    }
                }
            }

        });
    }

    //Refreshes the livechat every second
    /*private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            final TextView textViewResult = (TextView) findViewById(R.id.text_view_result);
            final ScrollView scrollView = (ScrollView) findViewById(R.id.chatSV);

            Call<LiveChatMessages> call = jsonPlaceHolderApi.getPosts();
            call.enqueue(new Callback<LiveChatMessages>() {
                @Override
                public void onResponse(Call<LiveChatMessages> call, Response<LiveChatMessages> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code: " + response.code());
                        return;
                    }
                    LiveChatMessages liveChatMessagesResponse = response.body();
                    List<LiveChatMessages.Items> items = liveChatMessagesResponse.getItems();

                    String content = "";
                    for (LiveChatMessages.Items item : items) {
                        content += item.getSnippet().textMessageDetails.getMessageText() + "\n";
                    }
                    textViewResult.setText(content);
                }

                @Override
                public void onFailure(Call<LiveChatMessages> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });

            //makes the livechat scroll automatically when the user is at the bottom
            int diff = scrollView.getChildAt(0).getHeight() - 1393 - scrollView.getScrollY();
            if (diff < 250){
                int bottom = scrollView.getChildAt(0).getBottom() + scrollView.getPaddingBottom();
                int delta = bottom - scrollView.getScrollY() - scrollView.getHeight();
                scrollView.smoothScrollBy(0, delta);
            }

            customHandler.postDelayed(this, 1000);
        }
    };*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Window window = getWindow();
        ActionBar actionBar = getSupportActionBar();
        final com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView third_party_player_view = findViewById(R.id.third_party_player_view);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            third_party_player_view.enterFullScreen();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            // Hide ActionBar
            if (actionBar != null) {
                actionBar.hide();
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (third_party_player_view.isFullScreen()) {
                third_party_player_view.exitFullScreen();
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                // Show ActionBar

                if (actionBar != null) {
                    actionBar.show();
                }
            }
        }
    }
}
