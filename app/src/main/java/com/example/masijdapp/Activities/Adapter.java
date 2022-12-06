package com.example.masijdapp.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masijdapp.Interface.OnItemClick;
import com.example.masijdapp.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<masjid_listModel> masjidListModels;
    Context context;
    OnItemClick onItemClick;

    Adapter(List<masjid_listModel> masjidListModels, Context context, OnItemClick itemClick) {
        this.masjidListModels = masjidListModels;
        this.context = context;
        this.onItemClick = itemClick;
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
//                Log.d(String.valueOf(position),"position is");
                onItemClick.onclick(position, masjid_listModel.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return masjidListModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewname;
        TextView textViewadress;

        public ViewHolder(View view) {
            super(view);
            textViewname = itemView.findViewById(R.id.name);
            textViewadress = itemView.findViewById(R.id.address);
        }
    }
}



