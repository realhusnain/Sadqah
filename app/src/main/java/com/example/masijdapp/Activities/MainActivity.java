package com.example.masijdapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masijdapp.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    TextView drawerbtn, tv_DT_masjid, campainn;
    DrawerLayout drawerLayout;
    ImageView imageview;
    ImageView IV_dp;
    Uri imageUri;
    NavigationView navigationView;

    private static final int SELECT_PICTURE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerbtn = findViewById(R.id.drawerbutton);
        navigationView = findViewById(R.id.navigationview);
        tv_DT_masjid = findViewById(R.id.donatetomasjid);
        campainn = findViewById(R.id.campaign);
        drawerLayout = findViewById(R.id.drawerLayout);

        View header = navigationView.getHeaderView(0);
        imageview = header.findViewById(R.id.imagePicker);
        IV_dp = header.findViewById(R.id.sandraimg);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.DTM) {
                    Intent intent = new Intent(MainActivity.this, Donations.class);
                    startActivity(intent);
                } else if (id == R.id.logout) {
                    MainActivity.this.finish();
                } else if (id == R.id.compain) {
                    Intent intent = new Intent(MainActivity.this, Campaigns.class);
                    startActivity(intent);
                } else if (id == R.id.Feedbackk) {
                    Intent intent = new Intent(MainActivity.this, Feedback.class);
                    startActivity(intent);
                } else if (id == R.id.salah_time) {
                    Intent intent = new Intent(MainActivity.this, Azan.class);
                    startActivity(intent);
                }

                return true;
            }
        });
        drawerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);
                else drawerLayout.closeDrawer(GravityCompat.END);


            }
        });
        campainn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Campaigns.class);
                startActivity(intent);
            }
        });
        tv_DT_masjid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Donations.class);
                startActivity(intent);
            }
        });
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, SELECT_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            imageUri = data.getData();
            IV_dp.setImageURI(imageUri);

        }
    }
}