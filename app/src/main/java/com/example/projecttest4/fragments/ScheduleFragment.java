package com.example.projecttest4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.example.projecttest4.R;

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
        //View childView = viewFlipper.getChildAt(1);
        viewFlipper.setDisplayedChild(0);



        return view;
    }

}