package com.example.masijdapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.masijdapp.R;

public class LoginScreen extends AppCompatActivity {
    TextView register, loginBtn;
    EditText edPhone;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        register = findViewById(R.id.register);
        edPhone = findViewById(R.id.phone_no);
        loginBtn = findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check();
                Intent intent =new Intent(LoginScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, Signup.class));

            }
        });
    }

    public boolean check() {
        if (!edPhone.getText().toString().isEmpty()) {
            phone = edPhone.getText().toString().trim();
        } else {
            edPhone.setError("Phone Number Cannot Be Empty");

        }
        if (phone == null || phone.length() < 6 || phone.length() > 13) {
            Toast.makeText(this, "Invalid phoneNumber", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            Toast.makeText(this, "Valid PhoneNumber", Toast.LENGTH_SHORT).show();
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }
}