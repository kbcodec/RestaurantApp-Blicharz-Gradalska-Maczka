package com.example.projecttest4.controllers;

import com.example.projecttest4.models.ShiftTypes;
import com.example.projecttest4.models.User;
import com.example.projecttest4.services.ShiftTypesService;
import com.example.projecttest4.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftsTypesController {
    public ArrayList<ShiftTypes> getShiftsTypes() {
        ShiftTypesService us = new ShiftTypesService();
        try {
            return us.getAllShifts();
        } catch (SQLException e) {
            return null;
        }
    }
}
