package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TimeService {

    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    public void saveHours (String hours) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Working_Hours (hours) VALUES(?)");
        stmt.setString(1, hours);
        stmt.executeUpdate();
    }



}
