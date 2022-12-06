package com.example.masijdapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.masijdapp.Interface.OnItemClick;
import com.example.masijdapp.R;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
}








