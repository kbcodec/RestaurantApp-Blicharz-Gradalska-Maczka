package com.example.projecttest4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttest4.MenuViewInterface;
import com.example.projecttest4.R;
import com.example.projecttest4.models.Dish;

import java.util.ArrayList;

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

    @Override
    public void onBindViewHolder(@NonNull DishAdapter.ViewHolder holder, int position) {
        holder.tvDishId.setText(mDishes.get(position).getIdString());
        holder.tvDishName.setText(mDishes.get(position).getName());
        holder.tvDishIsVegan.setText(mDishes.get(position).getIsVeganString());
        holder.tvDishIsLactoseFree.setText(mDishes.get(position).getIsLactoseFreeString());
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDishId;
        public TextView tvDishName;
        public TextView tvDishIsVegan;
        public TextView tvDishIsLactoseFree;

        public ViewHolder(@NonNull View itemView, MenuViewInterface menuViewInterface) {
            super(itemView);
            tvDishId = itemView.findViewById(R.id.tvDishId);
            tvDishName = itemView.findViewById(R.id.tvDishName);
            tvDishIsVegan = itemView.findViewById(R.id.tvDishIsVegan);
            tvDishIsLactoseFree = itemView.findViewById(R.id.tvDishIsLactoseFree);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(menuViewInterface != null) {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION) {
                            menuViewInterface.onDishClick(position);
                        }
                    }
                }
            });
        }
    }
}
