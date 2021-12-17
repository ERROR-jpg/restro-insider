package com.example.restroinsider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restroinsider.notifications.Listener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button chef, waiter, counter;
    String TAG = "MainAc";
    ImageView infobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.counter);

        counter.setOnClickListener(v -> {
            startActivity(new Intent(this, order_status.class));
        });

        chef = findViewById(R.id.chef);
        chef.setOnClickListener(v -> {
            Intent Chef = new Intent(MainActivity.this, Chef_Main.class);
            startActivity(Chef);

        });
        waiter = findViewById(R.id.waiter);
        waiter.setOnClickListener(v -> {
            Intent Waiter = new Intent(MainActivity.this, Waiter_Main.class);
            startActivity(Waiter);
        });
        infobutton = findViewById(R.id.infobutton);
        infobutton.setOnClickListener(v -> {
            Intent Info = new Intent(MainActivity.this, info.class);
            startActivity(Info);
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("notify");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot s : snapshot.getChildren()){
                        try {
                            Log.d(TAG, "onDataChange: "+s.getValue());
                            Listener.notifyx(MainActivity.this,s.getValue(),false);
                        }catch (Exception e){
                            Log.d(TAG, "onDataChange: "+e);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}