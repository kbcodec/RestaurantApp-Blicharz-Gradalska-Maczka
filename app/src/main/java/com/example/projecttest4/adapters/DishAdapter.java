package com.example.projecttest4.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projecttest4.MenuViewInterface;
import com.example.projecttest4.R;
import com.example.projecttest4.models.Dish;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Klasa DishAdapter wyświetla listę dań w aplikacji.
 */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private MenuViewInterface menuViewInterface;
    Context context;
    private ArrayList<Dish> mDishes;

    public DishAdapter(Context context, MenuViewInterface menuViewInterface, ArrayList<Dish> mDishes) {
        this.context = context;
        this.menuViewInterface = menuViewInterface;
        this.mDishes = mDishes;
    }

    public DishAdapter(ArrayList<Dish> mDishes) {
        this.mDishes = mDishes;
    }

    @NonNull
    @Override
    public DishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dish, parent, false);
        return new ViewHolder(view, menuViewInterface);
    }

    /**
     * Metoda onBindViewHolder jest wywoływana, gdy widok potrzebuje wyświetlenia danych w odpowiednim elemencie.
     */
    @Override
    public void onBindViewHolder(@NonNull DishAdapter.ViewHolder holder, int position) {
        holder.tvDishName.setText(mDishes.get(position).getName());
        if(mDishes.get(position).getIsLactoseFree() == 1) {
            holder.ivLactoseFree.setImageResource(R.drawable.lactose_icon);
        }
        if(mDishes.get(position).getIsVegan() == 1) {
            holder.ivIsVegan.setImageResource(R.drawable.vegan_icon);
        }
    }

    /**
     * Metoda zwraca liczbę elementów w sekwencji dań.
     * @return zwraca liczbę elementów w sekwencji dań
     */
    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    /**
     * Klasa wewnętrzna ViewHolder zawiera odwołania do pól widoku, takich jak nazwa dania i ikony,
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDishName;
        public ImageView ivLactoseFree;
        public ImageView ivIsVegan;
        public LinearLayout llDish;

        public ViewHolder(@NonNull View itemView, MenuViewInterface menuViewInterface) {
            super(itemView);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            ivLactoseFree = itemView.findViewById(R.id.ivLactoseFree);
            ivIsVegan = itemView.findViewById(R.id.ivIsVegan);
            llDish = itemView.findViewById(R.id.linearDishLayout);


            itemView.setOnClickListener(v -> {
                if(menuViewInterface != null) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        menuViewInterface.onDishClick(position);
                    }
                }
            });
        }
    }

}
