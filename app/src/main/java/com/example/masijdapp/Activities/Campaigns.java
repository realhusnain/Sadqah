package com.example.masijdapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.masijdapp.R;

public class Campaigns extends AppCompatActivity {
    ImageView iv_play, iv_play2, iv_play3, iv_play4, iv_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaigns);
        iv_play = findViewById(R.id.homeImage);
        iv_play2 = findViewById(R.id.image2);
        iv_play3 = findViewById(R.id.image3);
        iv_play4 = findViewById(R.id.image4);
        iv_image = findViewById(R.id.picture11);



        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Campaigns.this, playimage.class);
                intent.putExtra("hi", R.drawable.nature3);
                startActivity(intent);

            }
        });
        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Campaigns.this, playvideo.class);
                startActivity(intent);

            }
        });
        iv_play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Campaigns.this, playvideo.class);
                startActivity(intent);

            }
        });
        iv_play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Campaigns.this, playvideo.class);
                startActivity(intent);

            }
        });
        iv_play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Campaigns.this, playvideo.class);
                startActivity(intent);

            }
        });


    }
}