package com.example.masijdapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.masijdapp.Interface.OnItemClick;
import com.example.masijdapp.R;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity implements OnItemClick {

    TextView show;
    List<masjid_listModel> masjidListModels = new ArrayList<>();
    Adapter adapter;
    Dialog dialog;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    CountryCodePicker cpp;
    TextView button;
    Spinner spinner;
    DatePickerDialog datePickerDialog;
    EditText phoneNumber, email, date;

    private static final String[] genderItem = {"Select Gender", "Male", "Female"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        cpp = findViewById(R.id.cpp);
        button = findViewById(R.id.Next);
        email = findViewById(R.id.Email);
        date = findViewById(R.id.D0B);
        spinner = findViewById(R.id.spinner1);
        date.setInputType(InputType.TYPE_NULL);

        phoneNumber = findViewById(R.id.phone_no);
        cpp.registerCarrierNumberEditText(phoneNumber);
        cpp.getFormattedFullNumber();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Signup.this, com.hbb20.R.layout.support_simple_spinner_dropdown_item, genderItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateValidate();

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneValidate();
                Intent intent = new Intent(Signup.this, DebitCardDetails.class);
                startActivity(intent);

            }
        });


        progressDialog = new ProgressDialog(Signup.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Loading...");
        dialog = new Dialog(this);
        dialog.setCancelable(true);

        show = findViewById(R.id.Masjid_near_home);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                showDialog();

            }
        });
    }


    private void showDialog() {
        masjidListModels.clear();
        getData();

        View view = this.getLayoutInflater().inflate(R.layout.list_items, null);
        dialog.setContentView(view);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new Adapter(masjidListModels, getApplicationContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://sadaqahnz.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);
        Call<MainMasjidModel> call = api.getMasjid_list();
        call.enqueue(new Callback<MainMasjidModel>() {
            @Override
            public void onResponse(@NonNull Call<MainMasjidModel> call, @NonNull Response<MainMasjidModel> response) {
                dialog.show();
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    MainMasjidModel masjidListModelss = response.body();
                    if (masjidListModelss != null) {
                        masjidListModels.addAll(masjidListModelss.getMasjid_list());

                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MainMasjidModel> call, Throwable t) {
                Toast.makeText(Signup.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onclick(int position, String name) {
        dialog.dismiss();
        show.setText(name);
    }

    public boolean isValidEmail(EditText email) {
        String emailInput = email.getText().toString();

        if (emailInput.isEmpty()) {
            email.setError("field cant be empty");
            return false;

        } else if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Email address is valid", Toast.LENGTH_SHORT).show();
            return true;

        } else if (!emailInput.isEmpty()) {
            Toast.makeText(this, "Email address is not valid", Toast.LENGTH_SHORT).show();
        }

        return false;
    }


    public void dateValidate() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(Signup.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();

    }

    private void phoneValidate() {


        cpp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {

            String phonee = phoneNumber.getText().toString();

            @Override
            public void onValidityChanged(boolean isValidNumber) {

                if (phonee.isEmpty()) {
                    phoneNumber.setError("Filed is empty");

                } else if (isValidNumber && !phonee.isEmpty()) {
                    Toast.makeText(Signup.this, "Phone number is Valid", Toast.LENGTH_SHORT).show();

                } else if (!phonee.isEmpty()) {
                    Toast.makeText(Signup.this, "Phone number is not Valid", Toast.LENGTH_SHORT).show();

                }
            }
        });
        isValidEmail(email);
    }

}


