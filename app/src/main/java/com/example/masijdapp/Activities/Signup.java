package com.example.masijdapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.masijdapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {

    TextView textView;
    List<masjid_listModel> masjidListModels = new ArrayList<>();
    Adapter adapter;
    Dialog dialog;
    RecyclerView recyclerView;
    ProgressDialog  progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        textView = findViewById(R.id.Masjid_near_home);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(Signup.this);
                progressDialog.setTitle("Please Wait...");
                progressDialog.setMessage("Loading...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();


                getData();
                showDialog();
                masjidListModels.clear();

            }
        });
    }

    private void showDialog() {
        dialog = new Dialog(this);
        View view = this.getLayoutInflater().inflate(R.layout.list_items, null);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new Adapter(masjidListModels, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog.setContentView(view);

        dialog.setCancelable(true);
        dialog.show();
    }

    public void getData() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://sadaqahnz.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);
        Call<MainMasjidModel> call = api.getMasjid_list();
        call.enqueue(new Callback<MainMasjidModel>() {
            @Override
            public void onResponse(Call<MainMasjidModel> call, Response<MainMasjidModel> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    MainMasjidModel masjidListModelss = response.body();
                    if (masjidListModelss != null) {
                        masjidListModels.addAll(masjidListModelss.getMasjid_list());

                        adapter.notifyDataSetChanged();

                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<MainMasjidModel> call, Throwable t) {
                Toast.makeText(Signup.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}








