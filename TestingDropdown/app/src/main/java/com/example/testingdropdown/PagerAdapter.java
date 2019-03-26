package com.example.testingdropdown;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int aNoOfTabs;
    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.aNoOfTabs = numberOfTabs;
    }



    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                EditImageFragment editImageFragment = new EditImageFragment();
                return editImageFragment;

            case 1:
                CropImage cropImage = new CropImage();
                return cropImage;
            default:
                return null;


        }



    }

    @Override
    public int getCount() {
        return aNoOfTabs;
    }
}
