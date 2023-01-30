package com.example.masijdapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.masijdapp.R;

public class playimage extends AppCompatActivity {
    ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playimage);
        imgview = findViewById(R.id.imageview1);
        Intent intent = getIntent();
        imgview.setImageResource(intent.getIntExtra("hi", 0));
    }

}

