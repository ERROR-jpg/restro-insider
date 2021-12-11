package com.example.restroinsider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Chef_Adapter extends RecyclerView.Adapter<Chef_Adapter.ViewHolder> {


    ArrayList<String> mOrders;
    Context context;

    public Chef_Adapter(ArrayList<String> orders) {
        mOrders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mOrderNumber.setText(mOrders.get(position));

        holder.button.setOnClickListener(v -> {



        });
    }


    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mOrderNumber;
        public Button button;



        public ViewHolder(View itemView) {
            super(itemView);

            this.button = (Button) itemView.findViewById(R.id.button);

            mOrderNumber = (TextView) itemView.findViewById(R.id.order_number);
        }

    }
}