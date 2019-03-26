package com.example.qgqwfqwe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add map fragment
        NearbyFragment nearbyFragment = (NearbyFragment) getSupportFragmentManager().findFragmentById();

        if (nearbyFragment == null) {
            nearbyFragment = NearbyFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_bar, nearbyFragment)
                    .commit();
        }
    }