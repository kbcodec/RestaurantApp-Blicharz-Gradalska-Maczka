package com.example.projecttest4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projecttest4.MenuViewInterface;
import com.example.projecttest4.R;
import com.example.projecttest4.adapters.DishAdapter;
import com.example.projecttest4.controllers.DishController;
import com.example.projecttest4.databinding.ActivityMainBinding;
import com.example.projecttest4.fragments.DishFragment;
import com.example.projecttest4.models.ChosenDish;
import com.example.projecttest4.models.Dish;

import java.util.ArrayList;

public class MenuFragment extends Fragment implements MenuViewInterface {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<Dish> mDishes;
    public ChosenDish chosenDish = new ChosenDish();

    ActivityMainBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,false);

        DishController dc = new DishController();
        mDishes = dc.getDishes();

        mRecyclerView = view.findViewById(R.id.viewList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mAdapter = new DishAdapter(view.getContext(), this, mDishes);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }


    @Override
    public void onDishClick(int position) {
        Toast.makeText(this.getContext(), mDishes.get(position).getIdString(), Toast.LENGTH_SHORT).show();
        chosenDish = new ChosenDish(
                mDishes.get(position).getId(),
                mDishes.get(position).getName(),
                mDishes.get(position).getIsVegan(),
                mDishes.get(position).getIsLactoseFree(),
                mDishes.get(position).getImgUrl()
        );

        Bundle args = new Bundle();
        args.putString("id", chosenDish.getIdString());
        args.putString("name", chosenDish.getName());
        args.putString("isVegan", chosenDish.getIsVeganString());
        args.putString("isLactoseFree", chosenDish.getIsLactoseFreeString());
        args.putString("imgUrl", chosenDish.getImgUrl());

        DishFragment dishFragment = new DishFragment();
        dishFragment.setArguments(args);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menuContainer, dishFragment);
        fragmentTransaction.commit();

    }
}