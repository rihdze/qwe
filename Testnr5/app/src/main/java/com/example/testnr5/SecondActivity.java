package com.example.testnr5;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SecondActivity extends AppCompatActivity {
    ImageView il;

    Button selectImage;
    Button saveImage;
    ImageView imageView;
    private int REQUEST_CODE = 1;
    private Bitmap bitmap;
    private SeekBar seekBarBrightness;
    private SeekBar seekBarContrast;
    private SeekBar seekBarSaturation;
    private PictureThread thread;


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


        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bestpicever);
        imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageBitmap(bitmap);

        thread = new PictureThread(il,bitmap);
        thread.start();

        seekBarBrightness = (SeekBar) findViewById(R.id.seekBarBrightness);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                imageView.setImageBitmap(thread.getTempBitmap());



                thread.adjustBrightness(seekBar.getProgress()-255);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                imageView.setImageBitmap(thread.getTempBitmap());

            }


        });


    }
}