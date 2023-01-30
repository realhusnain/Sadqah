package com.example.masijdapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.masijdapp.R;

public class playvideo extends AppCompatActivity {

    VideoView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        view = findViewById(R.id.videoview);

        String path1 ="http://dev.crymzee.com:8000/storage/videos/334a9e46-5126-409f-a2a3-7a1918ce1c84.mp4" ;
        Uri uri = Uri.parse(path1);
        view.setVideoURI(uri);
        view.start();

        MediaController mc = new MediaController(this);
        mc.setAnchorView(view);
        mc.setMediaPlayer(view);
        view.setMediaController(mc);

    }
}


