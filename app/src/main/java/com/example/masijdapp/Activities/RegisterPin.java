package com.example.masijdapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.masijdapp.R;

public class RegisterPin extends AppCompatActivity {

    TextView tv_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pin);
        tv_confirm=findViewById(R.id.Confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterPin.this,DebitCardDetails.class);
                startActivity(intent);
            }
        });
    }
}