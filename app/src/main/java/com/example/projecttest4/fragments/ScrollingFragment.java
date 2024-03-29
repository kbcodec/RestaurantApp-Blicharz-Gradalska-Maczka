package com.example.projecttest4.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projecttest4.R;

/**
 * Klasa ScrollingFragment jest klasą dziedziczącą po klasie Fragment w Android.
 * Klasa ta nadpisuje metodę onCreateView, która jest wywoływana podczas tworzenia widoku dla fragmentu.
 */
class ScrollingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling, container, false);
    }
}