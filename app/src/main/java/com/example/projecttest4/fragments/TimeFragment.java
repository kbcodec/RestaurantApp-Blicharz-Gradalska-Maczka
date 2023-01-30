package com.example.projecttest4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projecttest4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;


public class TimeFragment extends Fragment {

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

    public TimeFragment() {
    }

    public static TimeFragment newInstance(String param1, String param2) {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
//
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, container, false);
        timeView = view.findViewById(R.id.timeView);

        start = view.findViewById(R.id.start);
        stop = view.findViewById(R.id.stop);


        if (savedInstanceState != null) {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
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
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    private void runTimer() {

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);
                System.out.println(time);

                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);

            }
        });
    }

}