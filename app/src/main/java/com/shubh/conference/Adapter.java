package com.shubh.conference;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
ArrayList<String >list;
Context context;

    public Adapter(ArrayList<String  > list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ss=list.get(position);
    holder.updateS.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView updateS;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        updateS=itemView.findViewById(R.id.displayUpdates);
    }

    }

}


