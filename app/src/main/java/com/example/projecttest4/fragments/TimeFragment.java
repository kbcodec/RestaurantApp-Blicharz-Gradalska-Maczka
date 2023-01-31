package com.example.projecttest4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projecttest4.MyTimer;
import com.example.projecttest4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;


public class TimeFragment extends Fragment implements MyTimer.TimerRuning{

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    private FloatingActionButton start;
    private FloatingActionButton stop;
    private TextView timeView;
    private Handler handler;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private String mParam1;
    private String mParam2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyTimer.getInstance().setTimerRuningListener(this);
        View view = inflater.inflate(R.layout.fragment_time, container, false);
        timeView = view.findViewById(R.id.timeView);

        start = view.findViewById(R.id.start);
        stop = view.findViewById(R.id.stop);


        if (savedInstanceState != null) {

            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTimer.getInstance().startTimer(10);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTimer.getInstance().stopTimer();
            }
        });


        return view;
    }

//    public void onStart(View view){
//        running = true;
//    }
//
//    public void onStop(View view){
//        running = false;
//    }
//
//    public void onReset(View view){
//        running = false;
//        seconds = 0;
//    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onTimerChange(String remainSec, String startSec) {
        timeView.setText(startSec);

    }

    @Override
    public void onTimerStopped(String remainSec, String startSec) {
        timeView.setText(startSec);
    }
}