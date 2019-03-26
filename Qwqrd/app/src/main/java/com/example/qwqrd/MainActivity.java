package com.example.qwqrd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bitmap;
    private Bitmap bitmapTest;
    private SeekBar seekBarBrightness;
    private SeekBar seekBarContrast;
    private PictureThread thread;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int SELECT_A_PHOTO = 2;

    Button btn_take;
    Button btn_list;
    Button btn_load;
    ImageView imageView_photo;

    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// PRIEKSH KAMERAS, OPEN, SAVE, DROPDOWN MENUS
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        init();
        ImageView iv_photo;
        iv_photo = findViewById(R.id.image_preview);
        Glide.with(this).load(currentPhotoPath).into(iv_photo);


        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dog);
        bitmapTest = BitmapFactory.decodeFile(currentPhotoPath);




            imageView = (ImageView) findViewById(R.id.image_preview);
//        imageView.setImageBitmap(bitmapTest);
            imageView.setImageBitmap(bitmap);


//
//        Bitmap bitmaptest = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//        imageView.setImageBitmap(bitmaptest);




        thread = new PictureThread(imageView,bitmap);
        thread.start();

        seekBarBrightness = (SeekBar) findViewById(R.id.seekbar_brightness);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                thread.adjustBrightness(seekBar.getProgress()-255);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        seekBarContrast = (SeekBar) findViewById(R.id.seekbar_contrast);
        seekBarContrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                thread.adjustContrast(seekBar.getProgress()-100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_open) {
            dispatchTakePictureIntent();
            return true;
        }

        if (id == R.id.action_save) {
            galleryAddPic();
            return true;
        }
        if (id == R.id.action_settings){
            //create the intent to take a photo from the gallery
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            //start the intent with a request code
            startActivityForResult(intent, SELECT_A_PHOTO);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void initOnClickMethods(){

        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the intent to take a photo from the gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //start the intent with a request code
                startActivityForResult(intent, SELECT_A_PHOTO);

            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryAddPic();
            }
        });
    }


    public void init(){
        btn_take = findViewById(R.id.action_settings);
        btn_list = findViewById(R.id.action_save);
        btn_load = findViewById(R.id.action_open);

        imageView_photo = findViewById(R.id.image_preview);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            ImageView iv_photo;
            iv_photo = findViewById(R.id.image_preview);

            Glide.with(this).load(currentPhotoPath).into(iv_photo);
        }

        if (requestCode == SELECT_A_PHOTO && resultCode == RESULT_OK){
            Uri selectedPhoto = data.getData();
            ImageView iv_photo;
            iv_photo = findViewById(R.id.image_preview);

            Glide.with(this).load(selectedPhoto).into(iv_photo);
        }
    }




    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Something went wrong. Could not create file.", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.qwqrd.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,          /* prefix */
                ".jpg",         /* suffix */
                storageDir           /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}
