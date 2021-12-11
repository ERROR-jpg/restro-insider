package com.example.restroinsider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Waiter2 extends AppCompatActivity {
    private Button createorder;
    Spinner spinner;
    DatabaseReference dbref;
    //ValueEventListener listener;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    String TAG = "Waiter2";
    //List<Model> models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter2);
        spinner = (Spinner) findViewById(R.id.spinner);
        createorder = (Button) findViewById(R.id.createorder);
        dbref = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<String>();

        //Log.d(TAG, "onCreate: ");
       // List<Model2> models = new ArrayList<>();
        spinner.setAdapter(adapter);
        fetchdata();
        createorder.setOnClickListener(v -> opencreateorder());
    }

    public void opencreateorder() {
            Intent intent = new Intent(this, createorder.class);


            startActivity(intent);

    }



public void fetchdata() {
        dbref.child("Table").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    list.add(item.getValue(String.class));
                }
                adapter = new ArrayAdapter<String>(Waiter2.this, android.R.layout.simple_spinner_dropdown_item, list);
                Log.d(TAG, "onDataChange: list");
                //spinner.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
}