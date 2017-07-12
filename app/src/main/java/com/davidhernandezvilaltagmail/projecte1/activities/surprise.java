package com.davidhernandezvilaltagmail.projecte1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.davidhernandezvilaltagmail.projecte1.R;

public class surprise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise);
        VideoView videoView = (VideoView)findViewById(R.id.video);
        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);

        videoView.setVideoPath("/raw/video.mp4");

        videoView.start();
    }
}
