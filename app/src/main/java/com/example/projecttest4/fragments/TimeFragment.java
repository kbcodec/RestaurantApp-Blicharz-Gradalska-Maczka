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
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Timer;


public class TimeFragment extends Fragment implements MyTimer.TimerRuning{

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    private FloatingActionButton start;
    private FloatingActionButton stop;
    private TextView timeView;
    private Handler handler;

    //account
    GoogleSignInAccount previousAccount;
    GoogleSignInAccount currentAccount;
    private static boolean isFirstRun = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);
        timeView = view.findViewById(R.id.timeView);
        start = view.findViewById(R.id.start);
        stop = view.findViewById(R.id.stop);

        currentAccount  = GoogleSignIn.getLastSignedInAccount(view.getContext());
        System.out.println(currentAccount.getEmail());



        MyTimer.getInstance().setTimerRuningListener(this);

        if (savedInstanceState != null) {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTimer.getInstance().startTimer(view);
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


    @Override
    public void onPause() {
        super.onPause();
        previousAccount = currentAccount;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("ON RESUMEEEEEEEEEEE");
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