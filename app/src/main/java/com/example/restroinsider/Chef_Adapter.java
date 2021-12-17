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

import com.example.restroinsider.notifications.Listener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class Chef_Adapter extends RecyclerView.Adapter<Chef_Adapter.ViewHolder> {
    HashMap<String, HashMap<String, Object>> mOrders;
    NestedRvAdapter adapter;
    List<ChefModel> notes;

    private String TAG = "Chef_Adapter";

    public Chef_Adapter(HashMap<String, HashMap<String, Object>> orders, List<ChefModel> notes) {
        mOrders = orders;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_chef, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mOrderNumber.setText(mOrders.get(position));
        holder.notes.setText(notes.get(position).getNote());
        holder.pos.setText(notes.get(position).getPos());
        holder.tv.setText(notes.get(position).getTableNumber());
        holder.rv.setLayoutManager(new LinearLayoutManager(holder.rv.getContext()));
        holder.rv.setHasFixedSize(true);
        adapter = new NestedRvAdapter(Objects.requireNonNull(mOrders.get(notes.get(position).getTableNumber())));
        holder.rv.setAdapter(adapter);
        holder.orderComplete.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: clicked " + holder.getAdapterPosition());
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("orders");
            ref.child(holder.pos.getText().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        try {
                            HashMap<String, Object> orderDetails = (HashMap<String, Object>) snapshot.child("items").getValue();
                            mOrders.remove(notes.get(holder.getAdapterPosition()).getTableNumber());

                            HashMap<Object,Object> tmp = new HashMap<>();
                            tmp.put("items",orderDetails);
                            tmp.put("tableNumber",holder.tv.getText());
                            DatabaseReference d = FirebaseDatabase.getInstance().getReference("completedOrders");
                            String pushkey = d.push().getKey();
                            tmp.put("pos",pushkey);
                            assert pushkey != null;
                            d.child(pushkey).setValue(tmp);
//
                            notes.remove(holder.getAdapterPosition());
                            ref.child(holder.pos.getText().toString()).removeValue();
                            notifyDataSetChanged();

                            Listener.notifyx(v.getContext(), orderDetails , true);
                        } catch (Exception e) {
                            Log.e(TAG, "onDataChange2: " + e);
                        }
                    } else {
//                        Log.d(TAG, "onDataChange: " + notes.get(holder.getAdapterPosition()).getTableNumber());
                        //                        notes.remove(holder.getAdapterPosition());
//                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv, notes, pos, recyPos;
        RecyclerView rv;
        Button orderComplete;

        public ViewHolder(View itemView) {
            super(itemView);
            orderComplete = itemView.findViewById(R.id.orderComplete);
            recyPos = itemView.findViewById(R.id.recyPos);
            pos = itemView.findViewById(R.id.posHolder);
            rv = itemView.findViewById(R.id.myNestedChefRecy);
            tv = itemView.findViewById(R.id.tableNo);
            notes = itemView.findViewById(R.id.note);
        }

    }
}