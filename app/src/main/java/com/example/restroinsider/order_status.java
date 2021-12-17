package com.example.restroinsider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
import java.util.Map;
import java.util.Objects;

public class order_status extends AppCompatActivity {
    private String TAG = "orderStst";
    private RecyclerView recy;
    private static HashMap<String, HashMap<Object, HashMap<String, Object>>> x = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: order stat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        recy = findViewById(R.id.orderStatusRecy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setHasFixedSize(true);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("completedOrders");
        List<HashMap<Object, HashMap<String, Object>>> xy = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    xy.clear();
                }catch (Exception ignored){}
                if(snapshot.exists()){
                    for (DataSnapshot s : snapshot.getChildren()) {
                        HashMap<String, Object> holdr;
                        HashMap<Object, HashMap<String, Object>> holdr2 = new HashMap<>();
                        OrderModel model = s.getValue(OrderModel.class);
                        holdr = (HashMap<String, Object>) model.getItems();
                        holdr2.put(model.getTableNumber(), holdr);
                        xy.add(holdr2);
                        x.put(model.getPos(), holdr2);
                    }
                }
                OrderStatusAdapter adapter = new OrderStatusAdapter(xy);
                recy.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static void removeItem(HashMap<Object, HashMap<String, Object>> objectHashMapHashMap) {
        for (Map.Entry<String, HashMap<Object, HashMap<String, Object>>> entry : x.entrySet()) {
            if (Objects.equals(objectHashMapHashMap, entry.getValue())) {
                DatabaseReference r = FirebaseDatabase.getInstance().getReference("completedOrders");
                r.child(entry.getKey()).removeValue();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}