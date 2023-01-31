package com.example.projecttest4.controllers;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.models.User;
import com.example.projecttest4.services.TimeService;
import com.example.projecttest4.services.UserService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TimeController {

    public void setTime(String hours) {
        TimeService time = new TimeService();
        try {
            time.saveHours(hours);
        } catch (SQLException e) {
            time =null;
        }
    }

}
