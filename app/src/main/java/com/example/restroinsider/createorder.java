package com.example.restroinsider;

import static com.example.restroinsider.notifications.Utils.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    MyAdapter adapter;
    DatabaseReference dbref;
    ValueEventListener listener2;
    RecyclerView recyclerView;
    ArrayList<String> myArrayList = new ArrayList<>();
    String TAG = "createorder";
    EditText tableNumber,additionalNotes;
    Button newOrder,placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createorder);
        // myListView = (ListView) findViewById(R.id.listview1);
        //myListView.setAdapter(myArrayAdapter);
        tableNumber = findViewById(R.id.tableNumberrecord);
        additionalNotes = findViewById(R.id.extraStuff);
        newOrder = findViewById(R.id.placeNewOrder);
        placeOrder = findViewById(R.id.placeorder);
        dbref = FirebaseDatabase.getInstance().getReference();

        newOrder.setOnClickListener(v->{
            tableNumber.setVisibility(View.VISIBLE);
            additionalNotes.setVisibility(View.VISIBLE);
            placeOrder.setVisibility(View.VISIBLE);
        });

        placeOrder.setOnClickListener(v->{
            String pushKey = dbref.push().getKey();
            assert pushKey != null;
            try {
                boolean x = Integer.parseInt(tableNumber.getText().toString())>0;
                if (!(order.isEmpty() && !x)) {
                    dbref.child("orders/"+pushKey).child("items").setValue(order);
                    dbref.child("orders/"+pushKey).child("pos").setValue(pushKey);
                    dbref.child("orders/"+pushKey).child("note").setValue(additionalNotes.getText().toString());
                    dbref.child("orders/"+pushKey).child("tableNumber").setValue(tableNumber.getText().toString());
                    Toast.makeText(createorder.this, "Order Placed order:- "+order, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onCreate: "+tableNumber.getText());
                    try {
                        tableNumber.setText(null);
                        additionalNotes.setText(null);
                    } catch (Exception ignored) {
                    }
//                    tableNumber.setVisibility(View.GONE);
//                    additionalNotes.setVisibility(View.GONE);
//                    placeOrder.setVisibility(View.GONE);
                    Intent i = new Intent(createorder.this,createorder2.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(createorder.this, "Please make sure that all the fields are filled and at least 1 item is ordered", Toast.LENGTH_LONG).show();
                }
            } catch (Exception ignored) {
                Toast.makeText(createorder.this, "Please make sure that all the fields are filled and at least 1 item is ordered", Toast.LENGTH_LONG).show();
            }
        });

        recyclerView = findViewById(R.id.myRecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        List<Model> models = new ArrayList<>();
        dbref.child("menuitem").orderByChild("menuitemname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata : snapshot.getChildren()) {
                    if (mydata != null) {
                        Model m = mydata.getValue(Model.class);
                        assert m != null;
                        models.add(m);
                    }
                }
                adapter = new MyAdapter(models);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
