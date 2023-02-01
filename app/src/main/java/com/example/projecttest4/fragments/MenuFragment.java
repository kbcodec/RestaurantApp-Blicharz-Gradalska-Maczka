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

/**
 * Klasa służy do wyświetlania listy dań w formie recycler view
 * i przechodzenia do szczegółowego widoku dania.
 *
 */
public class MenuFragment extends Fragment implements MenuViewInterface {

    /**
     * Zmienna przechowuje widok recycler view, który będzie wyświetlany we fragmencie.
     */
    RecyclerView mRecyclerView;

    /**
     * Zmienna jest adapterem, który będzie łączyć dane z recycler view.
     */
    RecyclerView.Adapter mAdapter;

    /**
     * Zmienna jest listą dań, które będą wyświetlane w recycler view.
     */
    ArrayList<Dish> mDishes;

    /**
     * Zmienna jest obiektem wybranego dania.
     */
    public ChosenDish chosenDish = new ChosenDish();

    ActivityMainBinding binding;


    /**
     * Metoda wywoływana, gdy fragment jest tworzony.
     * Inicjalizuje widok recycler view i ustawia adapter.
     * @param inflater - inflater, który służy do wczytywania layoutu fragmentu.
     * @param container - kontener, do którego zostanie dodany fragment.
     * @param savedInstanceState - stan instancji, który może zostać wczytany przy ponownym tworzeniu fragmentu.
     * @return view -widok fragmentu.
     */
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


    /**
     * Metoda służy do reakcji na kliknięcie w element RecyclerView z listą dań.
     * @param position pozycja wybranego dania na liście
     */
    @Override
    public void onDishClick(int position) {
        chosenDish = new ChosenDish(
                mDishes.get(position).getId(),
                mDishes.get(position).getName(),
                mDishes.get(position).getWorthToRecommend(),
                mDishes.get(position).getCookingNotes(),
                mDishes.get(position).getPrice(),
                mDishes.get(position).getIsVegan(),
                mDishes.get(position).getIsLactoseFree(),
                mDishes.get(position).getImgUrl()
        );

        Bundle args = new Bundle();
        args.putString("id", chosenDish.getIdString());
        args.putString("name", chosenDish.getName());
        args.putString("worthRecommend", chosenDish.getWorthToRecommend());
        args.putString("cookingNotes", chosenDish.getCookingNotes());
        args.putString("price", chosenDish.getPriceString());
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