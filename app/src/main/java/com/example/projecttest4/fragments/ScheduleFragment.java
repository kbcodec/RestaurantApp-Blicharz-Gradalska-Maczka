package com.example.projecttest4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import com.example.projecttest4.R;
import com.example.projecttest4.controllers.ShiftsTypesController;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.ShiftTypes;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.SQLException;
import java.util.ArrayList;

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

        // set Spinner Workers
        Spinner chooseWorkerSpinner = view.findViewById(R.id.chooseWorkerSpinner);
        ArrayList<String> emails = null;
        try {
            emails = adjustListOfUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterWorkers = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, emails);
        adapterWorkers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseWorkerSpinner.setAdapter(adapterWorkers);

        // set Spinner Shift
        Spinner chooseShiftSpinner = view.findViewById(R.id.chooseShiftSpinner);
        ArrayList<String> shifts = null;
        try {
            shifts = adjustListOfShifts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterShifts = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, shifts);
        adapterShifts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseShiftSpinner.setAdapter(adapterShifts);

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

    private ArrayList<String> adjustListOfUsers() throws SQLException {
        UserController uc = new UserController();
        ArrayList<User> allUsers = uc.getUsers();

        ArrayList<String> emails = new ArrayList<>();

        for (User user : allUsers){
            emails.add(user.getEmail());
        }

        return emails;
    }

    private ArrayList<String> adjustListOfShifts() throws SQLException {
        ShiftsTypesController sc = new ShiftsTypesController();
        ArrayList<ShiftTypes> allShifts = sc.getShiftsTypes();

        ArrayList<String> shifts = new ArrayList<>();

        for (ShiftTypes shift : allShifts){
            shifts.add(shift.getShiftHours());
        }

        return shifts;
    }


}