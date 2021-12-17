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
import java.util.Objects;

public class OrderStatusNestedAdapter extends RecyclerView.Adapter<OrderStatusNestedAdapter.viewHolder> {
    HashMap<String ,Object> x;
    List<String> keys=new ArrayList<>();
    OrderStatusNestedAdapter(HashMap<String ,Object> x){
        this.x = x;
        keys.addAll(x.keySet());
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_complete_nested,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String key = keys.get(position);
        holder.name.setText(key);
        holder.number.setText(Objects.requireNonNull(x.get(key)).toString());
    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView name,number;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.myNestedChefRecyItemText1);
            number = itemView.findViewById(R.id.myNestedChefRecyItemCountText1);
        }
    }
}
