package com.example.restroinsider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NestedRvAdapter extends RecyclerView.Adapter<NestedRvAdapter.viewHolder> {
    HashMap<String, Object> x;
    List<String> keys = new ArrayList<>();

    NestedRvAdapter(HashMap<String, Object> x) {
        this.x = x;
        keys.addAll(x.keySet());
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_chef_nested,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(keys.get(position));
        holder.amt.setText(x.get(String.valueOf(holder.name.getText())).toString());
    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView name,amt;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.myNestedChefRecyItemText);
            amt = itemView.findViewById(R.id.myNestedChefRecyItemCountText);
        }
    }
}
