package com.example.restroinsider;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addtables extends AppCompatActivity {
private Button addtable;
private EditText tablenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtables);
        addtable = findViewById(R.id.addtable);
        tablenumber = findViewById(R.id.tablenumber);
        addtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> m=new HashMap<String, Object>();
                m.put("Table", tablenumber.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("tablenumber").push().setValue(m);

                Toast.makeText(addtables.this, "New Table Added ! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}