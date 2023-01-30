package com.example.projecttest4.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.projecttest4.R;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class DishFragment extends Fragment {

    @Nullable
    TextView tvChosenDishName, tvWorthRecommend, tvCookingNotes, tvPrice;
    ImageView imageView, ivLactoseFreeDish, ivIsVeganDish;
    Button backBtn;

    GoogleSignInOptions gso;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish,container,false);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(view.getContext());

        ScrollView scrollView = view.findViewById(R.id.scrollViewDescribe);
        scrollView.bringToFront();

        tvChosenDishName = view.findViewById(R.id.tvChosenDishName);
        tvWorthRecommend = view.findViewById(R.id.tvRecommend);
        tvCookingNotes = view.findViewById(R.id.tvCookingNotes);
        tvPrice = view.findViewById(R.id.tvPrice);
        ivLactoseFreeDish = view.findViewById(R.id.ivLactoseFreeDish);
        ivIsVeganDish = view.findViewById(R.id.ivIsVeganDish);

        tvChosenDishName.setText(getArguments().getString("name"));
        tvPrice.setText(getArguments().getString("price") + " zł");
        if(getArguments().getString("isVegan").equals("1")) {
            ivIsVeganDish.setImageResource(R.drawable.vegan_icon);
        }
        if(getArguments().getString("isLactoseFree").equals("1")) {
            ivLactoseFreeDish.setImageResource(R.drawable.lactose_icon);
        }

        adjustDishFragment(acct);


        imageView = view.findViewById(R.id.imgDish);

        Glide.with(this).load(getArguments().getString("imgUrl")).into(imageView);

        backBtn = view.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(v -> {
            MenuFragment menuFragment = new MenuFragment();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.dishContainer, menuFragment);
            fragmentTransaction.commit();
        });

        return view;
    }

    private void adjustDishFragment (GoogleSignInAccount acct) {
        if(acct != null) {
            UserController uc = new UserController();
            User userFetchByEmail = uc.getUser(acct.getEmail());
            int userFetchByEmailPosition = userFetchByEmail.getPosition();

            if(userFetchByEmailPosition == 2) { //COOK
                tvCookingNotes.setText(getArguments().getString("cookingNotes"));
                tvWorthRecommend.setText("");
            } else if (userFetchByEmailPosition == 3) { //WAITER
                tvWorthRecommend.setText(getArguments().getString("worthRecommend"));
                tvCookingNotes.setText("");
            } else if (userFetchByEmailPosition == 1) { //BOSS
                tvWorthRecommend.setText(getArguments().getString("worthRecommend"));
                tvCookingNotes.setText(getArguments().getString("cookingNotes"));
            }
        } else {
            tvWorthRecommend.setText("");
            tvCookingNotes.setText("");
        }
    }

}