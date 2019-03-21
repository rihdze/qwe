package com.example.testnr4;
//HOW TO TRANSFER PIC FROM ONE ACTIVITY TO ANOTHER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    ImageView il;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        il = (ImageView) findViewById(R.id.imageView2);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            int resid = bundle.getInt("resId");
            il.setImageResource(resid);


        }
    }
}
