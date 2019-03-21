package com.example.testnr7.

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

//import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        saveImage = (Button) findViewById(R.id.saveButton);
        selectImage = (Button) findViewById(R.id.btnImageGallery);

        imageView = (ImageView) findViewById(R.id.chooseImage);





        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
        imageView = (ImageView) findViewById(R.id.chooseImage);
        imageView.setImageBitmap(bitmap);

//        thread = new PictureThread(imageView,bitmap);
//        thread.start();
//
//        seekBarBrightness = (SeekBar) findViewById(R.id.seekBarBrightness);
//        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                imageView.setImageBitmap(thread.getTempBitmap());
//                thread.adjustBrightness(seekBar.getProgress()-255);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
////                imageView.setImageBitmap(thread.getTempBitmap());
//
//            }
//
//
//        });










        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
            }
        });
    }

    public void send (View v){


        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        i.putExtra("resId", R.drawable.bestpicever);
        startActivity(i);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }





//    @Override
//    public boolean onCreateOptionsMenu (Menu menu){
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
//
//        return true;
//    }




}