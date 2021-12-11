package com.example.restroinsider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.util.List;

public class createorder extends AppCompatActivity {
    ListView myListView;
    DatabaseReference dbref;
    ValueEventListener listener2;
    RecyclerView recyclerView;
    ArrayList<String> myArrayList = new ArrayList<>();
    String TAG = "createorder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createorder);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArrayList);
        // myListView = (ListView) findViewById(R.id.listview1);
        //myListView.setAdapter(myArrayAdapter);
        recyclerView = findViewById(R.id.myRecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        List<Model> models = new ArrayList<>();
        dbref = FirebaseDatabase.getInstance().getReference("menuitem");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata : snapshot.getChildren()) {
                    if (mydata != null) {
                        Model m = mydata.getValue(Model.class);
                        assert m != null;
                        models.add(m);
                    }
                }
                MyAdapter adapter = new MyAdapter(models);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
