package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.transform.Result;

public class UserService {

    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    public ArrayList<User> getAllUsers() throws SQLException {
        connection = restaurantConnect.connectToDB();
        String query = "SELECT * FROM EMPLOYEES";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<User> listOfAllUsers = new ArrayList<>();
        while(rs.next()) {
            User newUser = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            );
            listOfAllUsers.add(newUser);
        }
        return listOfAllUsers;
    }

    public User getUser(String email) throws SQLException {
        connection = restaurantConnect.connectToDB();
        String query = "SELECT * FROM EMPLOYEES WHERE email = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        User user = new User();

        while(rs.next()) {
            user = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5));
        }



        return user;
    }

}
