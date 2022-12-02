package com.example.masijdapp.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masijdapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    List<masjid_listModel> masjidListModels;
    Context context;

    Adapter(List<masjid_listModel> masjidListModels, Context context) {
        this.masjidListModels = masjidListModels;
        this.context = context;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        masjid_listModel masjid_listModel = masjidListModels.get(position);

        holder.textViewname.setText(masjid_listModel.getName());
        holder.textViewadress.setText(masjid_listModel.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicking", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return masjidListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView textViewname;
        TextView textViewadress;

        public ViewHolder(View view) {
            super(view);
            textViewname = itemView.findViewById(R.id.name);
            textViewadress = itemView.findViewById(R.id.address);
        }
    }
}



