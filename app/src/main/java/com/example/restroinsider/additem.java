package com.example.restroinsider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class additem extends AppCompatActivity {
    private Button additem;
    private EditText menuitemname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        additem = findViewById(R.id.additem);
        menuitemname = findViewById(R.id.menuitemname);
        additem.setOnClickListener(view -> {
            HashMap<String, Object> m= new HashMap<>();
            m.put("menuitemname", menuitemname.getText().toString());
            FirebaseDatabase.getInstance().getReference().child("menuitem").push().setValue(m);

            Toast.makeText(additem.this, "Menu Item Added ! ", Toast.LENGTH_SHORT).show();
        });
    }
}