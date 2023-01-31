package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.controllers.TimeController;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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

    public void saveEmployment (int id_hours, int emply_id) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Employment (id_working_Hours, id_employee) VALUES(?,?)");

        stmt.setInt(1, id_hours);
        stmt.setInt(2, emply_id);
        stmt.executeUpdate();
    }


    public int getLastId() throws SQLException {
        connection =  restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("SELECT IDENT_CURRENT('Working_Hours')");
        ResultSet rs = stmt.executeQuery();
        int result = 999;
        while(rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }

}
