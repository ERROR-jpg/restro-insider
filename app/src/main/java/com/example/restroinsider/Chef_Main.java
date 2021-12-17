package com.example.restroinsider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chef_Main extends AppCompatActivity {

    String TAG = "Chef";
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Chef_Adapter mAdapter;
    DatabaseReference dbref;
    HashMap<String,HashMap<String,Object>> orders = new HashMap<>();
    List<ChefModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        dbref = FirebaseDatabase.getInstance().getReference();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbref.child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!notes.isEmpty()){
                    notes.clear();
                }
                for (DataSnapshot s : snapshot.getChildren()) {
                    HashMap<String,Object> tmp = (HashMap<String, Object>) s.child("items").getValue();
                    ChefModel m = s.getValue(ChefModel.class);
                    orders.put(m.tableNumber, tmp);
                    notes.add(m);
                    mAdapter = new Chef_Adapter(orders,notes);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

/*
String tmp2 = tmp.replace("{","").replace("}","");
                    String[] tmp3;
                    if(tmp2.contains(",")){
                        tmp3 = tmp2.split(",");
                    }else{
                        tmp2 = tmp2+",";
                        tmp3 = tmp2.split(",");
                    }
                    for(String sx: tmp3){
                        String[] sxx = sx.split("=");
                        order.put(sxx[0],sxx[1]);
                        Log.d(TAG, "onDataChange: "+order);
                    }
 */