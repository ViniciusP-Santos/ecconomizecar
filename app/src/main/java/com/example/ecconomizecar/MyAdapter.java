package com.example.ecconomizecar;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Average> averageArrayList;

    public MyAdapter(Context context, ArrayList<Average> userArrayList) {
        this.context = context;
        this.averageArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Average average = averageArrayList.get(position);

        holder.data.setText(average.data);
        holder.kms.setText(String.valueOf(average.kms));
        holder.litros.setText(String.valueOf(average.litros));
        holder.media.setText(average.media);
    }

    @Override
    public int getItemCount() {
        return averageArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView data, kms, litros, media;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.tvData);
            kms = itemView.findViewById(R.id.tvKms);
            litros = itemView.findViewById(R.id.tvLitros);
            media = itemView.findViewById(R.id.tvMedia);
        }
    }
}
