package com.example.restroinsider;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Chef_Main extends AppCompatActivity {



    ArrayList<String> mOrders;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mOrders = new ArrayList<>();
        mOrders.add("ORDER 1");
        mOrders.add("ORDER 2");
        mOrders.add("ORDER 3");
        mOrders.add("ORDER 4");
        mOrders.add("ORDER 5");
        mOrders.add("ORDER 6");
        mOrders.add("ORDER 7");
        mOrders.add("ORDER 8");
        mOrders.add("ORDER 9");
        mOrders.add("ORDER 10");
        mOrders.add("ORDER 11");
        mOrders.add("ORDER 12");
        mOrders.add("ORDER 13");
        mOrders.add("ORDER 14");
        mOrders.add("ORDER 15");
        mOrders.add("ORDER 16");
        mOrders.add("ORDER 17");
        mOrders.add("ORDER 18");
        mOrders.add("ORDER 19");
        mOrders.add("ORDER 20");
        mOrders.add("ORDER 21");


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Chef_Adapter(mOrders);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}