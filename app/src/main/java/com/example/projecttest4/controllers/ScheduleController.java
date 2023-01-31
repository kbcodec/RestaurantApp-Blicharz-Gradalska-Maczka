package com.example.projecttest4.controllers;

import com.example.projecttest4.services.ScheduleService;
import com.example.projecttest4.services.TimeService;

import java.sql.Date;
import java.sql.SQLException;

public class ScheduleController {
    public void addSchedule(Date date, int type) {
        ScheduleService ss = new ScheduleService();
        try {
            ss.addSchedule(date, type);
        } catch (SQLException e) {
            ss =null;
        }
    }

    public int getLastId() {
        ScheduleService ss = new ScheduleService();
        try {
            return ss.getLastId();
        } catch (SQLException e) {
            return 0;
        }
    }
}
