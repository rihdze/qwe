package com.example.testnr4;
//HOW TO TRANSFER PIC FROM ONE ACTIVITY TO ANOTHER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void send (View v){


        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        i.putExtra("resId", R.drawable.bestpicever);
        startActivity(i);
    }
}
