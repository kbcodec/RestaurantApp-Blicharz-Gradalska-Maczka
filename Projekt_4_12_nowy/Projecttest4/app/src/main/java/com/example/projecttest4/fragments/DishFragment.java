package com.example.projecttest4.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.service.controls.Control;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projecttest4.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class DishFragment extends Fragment {

    @Nullable
    TextView tvChosenDishId, tvChosenDishName, tvChosenDishIsVegan, tvChosenDishIsLactoseFree;
    Button backBtn;

    GoogleSignInOptions gso;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish,container,false);


        tvChosenDishId = view.findViewById(R.id.tvChosenDishId);
        tvChosenDishName = view.findViewById(R.id.tvChosenDishName);
        tvChosenDishIsVegan = view.findViewById(R.id.tvChosenDishIsVegan);
        tvChosenDishIsLactoseFree = view.findViewById(R.id.tvChosenDishIsLactoseFree);

        tvChosenDishId.setText(getArguments().getString("id"));
        tvChosenDishName.setText(getArguments().getString("name"));
        tvChosenDishIsVegan.setText(getArguments().getString("isVegan"));
        tvChosenDishIsLactoseFree.setText(getArguments().getString("isLactoseFree"));

        backBtn = view.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment menuFragment = new MenuFragment();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.dishContainer, menuFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}