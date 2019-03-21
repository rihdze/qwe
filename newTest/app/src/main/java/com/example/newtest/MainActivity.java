package com.example.newtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    final Handler handler = new Handler();
    private static final int SELECT_PHOTO = 100;
    private ImageView chooseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseImage=(ImageView) findViewById(R.id.chooseImage);
    }

    public void changeLayout(View view){
        openGallery();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.test_layout);  //Do something after 100ms
            }
        }, 1000);

        ;

    }

    public void ChooseImage(View v){
        openGallery();
    }

    private void openGallery(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    if(selectedImage !=null){
                        chooseImage.setImageURI(selectedImage);
                    }
                }
        }
    }



}
