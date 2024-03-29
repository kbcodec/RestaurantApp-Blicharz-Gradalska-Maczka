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

/**
 * Klasa DishService jest klasą odpowiedzialną za łączenie się i pobieranie danych z bazy danych dotyczących potraw
 */
public class DishService {

    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    /**
     * Metoda zwraca listę wszystkich potraw w bazie danych.
     * Pobiera dane za pomocą zapytania SQL i tworzy listę obiektów klasy Dish.
     * @return listOfAllDishes - listę obiektów klasy Dish
     * @throws SQLException
     */
    public ArrayList<Dish> getAllDishes () throws SQLException {
        connection = restaurantConnect.connectToDB();
        String query = "select d.dish_ID, d.nameDish, d.worthToRecommend, d.cookingNotes, d.price, d.isVegan, d.isLactoseFree, i.image_link " +
                "from dishes d " +
                "inner join images i on i.image_id = d.image_id;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<Dish> listOfAllDishes = new ArrayList<>();
        while(rs.next()) {
            Dish newDish = new Dish(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getString(8)
            );
            listOfAllDishes.add(newDish);
        }
        return listOfAllDishes;
    }

    /**
     * Metoda zwraca jedną potrawę o danym identyfikatorze.
     * Pobiera dane za pomocą zapytania SQL i tworzy obiekt klasy Dish o podanym id
     * @param id - id konkretnego dania
     * @return dish - obiekt klasy Dish
     * @throws SQLException
     */
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
                    rs.getString(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getString(8)
            );
        }

        return dish;
    }
}
