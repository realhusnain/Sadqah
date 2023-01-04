package com.example.masijdapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.masijdapp.R;

public class DebitCardDetails extends AppCompatActivity {
    TextView tv_skip,tv_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_card_details);
        tv_confirm=findViewById(R.id.Confirm);
        tv_skip=findViewById(R.id.Skip);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DebitCardDetails.this,RegisterPin.class);
                startActivity(intent);
            }
        });


        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DebitCardDetails.this,RegisterPin.class);
                startActivity(intent);
            }
        });
    }
}