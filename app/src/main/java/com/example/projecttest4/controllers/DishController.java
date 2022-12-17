package com.example.projecttest4.controllers;

import com.example.projecttest4.models.Dish;
import com.example.projecttest4.models.User;
import com.example.projecttest4.services.DishService;
import com.example.projecttest4.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class DishController {
    public ArrayList<Dish> getDishes() {
        DishService ds = new DishService();
        try {
            return ds.getAllDishes();
        } catch (SQLException e) {
            return null;
        }
    }

    public Dish getDish(int id) {
        DishService ds = new DishService();
        try {
            return ds.getDish(id);
        } catch (SQLException e) {
            return null;
        }
    }
}
