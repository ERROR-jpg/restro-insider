package com.example.restroinsider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;



public class Waiter_Main extends AppCompatActivity  {
    public Button button , button2 , button3 , button6 , button7;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_main);

        button =(Button) findViewById(R.id.button);
        button3 =(Button) findViewById(R.id.button3);
        button2 =(Button) findViewById(R.id.button2);
        button7 =(Button) findViewById(R.id.button7);
        button7.setOnClickListener(v -> openorder_status());


        button3.setOnClickListener(new OnClickListener(){


            @Override
            public void onClick(View v){
                openaddtables();
            }
        });





        button2.setOnClickListener(new OnClickListener(){


            @Override
            public void onClick(View v){
                openadditem();
            }
        });
        button.setOnClickListener(v -> openWaiter2());
    }

    private void openorder_status() {
        Intent intent = new Intent(this,order_status.class);


        startActivity(intent);
    }

    public void openWaiter2(){
        Intent intent = new Intent(this,Waiter2.class);


        startActivity(intent);

    }
    public void openadditem() {
        Intent intent = new Intent(this, additem.class);


        startActivity(intent);

    }

    public void openaddtables() {
        Intent intent = new Intent(this, addtables.class);


        startActivity(intent);

    }




}