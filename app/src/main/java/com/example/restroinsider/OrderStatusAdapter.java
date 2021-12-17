package com.example.restroinsider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.viewHolder> {
    List<HashMap<Object,HashMap<String,Object>>> x;
    OrderStatusAdapter(List<HashMap<Object,HashMap<String,Object>>> x){
        this.x = x;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_complete,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HashMap<Object,HashMap<String ,Object>> current = x.get(position);
        List<Object> xy = new ArrayList<>(current.keySet());
        holder.tableNo.setText(xy.get(0).toString());
        holder.del.setOnClickListener(v->{
            Log.d("TAG", "onBindViewHolder: "+x.get(holder.getAdapterPosition()));
            order_status.removeItem(x.get(holder.getAdapterPosition()));
            x.remove(holder.getAdapterPosition());
            notifyDataSetChanged();
        });
        List<HashMap<String ,Object>> xx = new ArrayList<>(current.values());
        holder.rv.setLayoutManager(new LinearLayoutManager(holder.rv.getContext()));
        holder.rv.setHasFixedSize(true);
        holder.rv.setAdapter(new OrderStatusNestedAdapter(xx.get(0)));
    }

    @Override
    public int getItemCount() {
        return x.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView tableNo;
        RecyclerView rv;
        Button del;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.myNestedChefRecy1);
            tableNo = itemView.findViewById(R.id.tableNo1);
            del = itemView.findViewById(R.id.orderComplete1);
        }
    }
}
