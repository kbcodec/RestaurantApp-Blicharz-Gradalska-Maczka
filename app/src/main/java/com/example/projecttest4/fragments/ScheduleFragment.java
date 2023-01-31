package com.example.projecttest4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.example.projecttest4.R;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ScheduleFragment extends Fragment {


    public ScheduleFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ViewFlipper viewFlipper = view.findViewById(R.id.change_view);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(view.getContext());

        int numOfView = adjustScheduleFragment(acct);
        viewFlipper.setDisplayedChild(numOfView);



        return view;
    }

    private int adjustScheduleFragment (GoogleSignInAccount acct) {
        UserController uc = new UserController();
        User userFetchByEmail = uc.getUser(acct.getEmail());
        int userFetchByEmailPosition = userFetchByEmail.getPosition();

        int userPosition = 0;

        if(userFetchByEmailPosition == 2 || userFetchByEmailPosition == 3) { //COOK AND WAITER
            userPosition = 0;
        } else if (userFetchByEmailPosition == 1) { //BOSS
            userPosition = 1;
        }
        return userPosition;
    }

}