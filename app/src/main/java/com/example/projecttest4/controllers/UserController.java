package com.example.projecttest4.controllers;

import com.example.projecttest4.models.User;
import com.example.projecttest4.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    public ArrayList<User> getUsers() {
        UserService us = new UserService();
        try {
            return us.getAllUsers();
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUser(String email) {
        UserService us = new UserService();
        try {
            return us.getUser(email);
        } catch (SQLException e) {
            return null;
        }
    }
}
