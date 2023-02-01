package com.example.projecttest4.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projecttest4.MyTimer;
import com.example.projecttest4.R;
import com.example.projecttest4.controllers.TimeController;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Klasa służy do odliczania czasu pracy użytkownika.
 * Zawiera ona funkcjonalność pozwalającą na rozpoczęcie, zatrzymanie i zapisanie czasu pracy.
 */
public class TimeFragment extends Fragment implements MyTimer.TimerRuning{

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    private FloatingActionButton start;
    private FloatingActionButton stop;
    private FloatingActionButton save;
    private TextView timeView;
    private Handler handler;

    //account
    GoogleSignInAccount previousAccount;
    GoogleSignInAccount currentAccount;
    private static boolean isFirstRun = true;


    /**
     * W metodzie onCreateView znajdują się funkcje odpowiadające za działanie przycisków start i stop.
     * @param inflater - inflater, który służy do wczytywania layoutu fragmentu.
     * @param container - kontener, do którego zostanie dodany fragment.
     * @param savedInstanceState - stan instancji, który może zostać wczytany przy ponownym tworzeniu fragmentu.
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);
        timeView = view.findViewById(R.id.timeView);
        start = view.findViewById(R.id.start);
        stop = view.findViewById(R.id.stop);

        currentAccount  = GoogleSignIn.getLastSignedInAccount(view.getContext());

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
                String timeSaving = String.valueOf(timeView.getText());
                currentAccount  = GoogleSignIn.getLastSignedInAccount(view.getContext());
                User user = new UserController().getUser(currentAccount.getEmail());


                TimeController timeToAdd = new TimeController();
                int last_id = timeToAdd.getLastId();
                timeToAdd.setEmployment(last_id, user.getId());
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
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    /**
     * Metoda ustawia aktualny czas na polu tekstowym timeView.
     * @param remainSec - wartosc końcowa
     * @param startSec - początek odlicznego czasu (sekundy)
     */
    @Override
    public void onTimerChange(String remainSec, String startSec) {
        timeView.setText(startSec);
    }


    /**
     * Metoda ustawia czas na polu tekstowym timeView po zatrzymaniu odliczania.
     * @param remainSec - wartosc końcowa
     * @param startSec - początek odlicznego czasu (sekundy)
     */
    @Override
    public void onTimerStopped(String remainSec, String startSec) {
        timeView.setText(startSec);
    }



}