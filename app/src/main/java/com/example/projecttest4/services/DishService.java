package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.models.Dish;
import com.example.projecttest4.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DishService {

    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    public ArrayList<Dish> getAllDishes () throws SQLException {
        connection = restaurantConnect.connectToDB();
        String query = "SELECT * FROM DISHES";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<Dish> listOfAllDishes = new ArrayList<>();
        while(rs.next()) {
            Dish newDish = new Dish(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(6),
                    rs.getInt(7)
            );
            listOfAllDishes.add(newDish);
        }
        return listOfAllDishes;
    }

    public Dish getDish(int id) throws SQLException {
        connection = restaurantConnect.connectToDB();
        String query = "SELECT * FROM DISHES WHERE dish_ID = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        ResultSet rs = pstmt.executeQuery();
        Dish dish = new Dish();

        while(rs.next()) {
            dish = new Dish(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(5),
                    rs.getInt(6)
            );
        }

        return dish;
    }
}
