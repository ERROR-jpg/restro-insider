package com.example.restroinsider;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Button chef;
    Button waiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chef = (Button) findViewById(R.id.chef);
        chef.setOnClickListener(v -> {
            Intent Chef = new Intent(MainActivity.this, Chef_Main.class);
            startActivity(Chef);
        });
        waiter = (Button) findViewById(R.id.waiter);
        waiter.setOnClickListener(v -> {
            Intent Waiter = new Intent(MainActivity.this, Waiter_Main.class);
            startActivity(Waiter);
        });

    }
}