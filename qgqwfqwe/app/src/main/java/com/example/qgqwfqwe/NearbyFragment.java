package com.example.qgqwfqwe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NearbyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NearbyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NearbyFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private TextView distance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nearby, container, false);

        final SeekBar seekBar  = (SeekBar) rootView.findViewById(R.id.seekBar);

        distance = (TextView) rootView.findViewById(R.id.distance);

        seekBar.setProgress(50);
        distance.setText("ass");

        seekBar.setOnSeekBarChangeListener(this);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        distance.setText("" + i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }}
