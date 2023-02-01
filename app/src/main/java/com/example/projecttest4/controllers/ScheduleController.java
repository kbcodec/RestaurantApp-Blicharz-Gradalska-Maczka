package com.example.projecttest4.controllers;

import com.example.projecttest4.models.Schedule;
import com.example.projecttest4.services.ScheduleService;
import com.example.projecttest4.services.TimeService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Schedule> getSchedulesForEmployee(int id) {
        ScheduleService ss = new ScheduleService();
        try {
            return ss.getSchedulesForEmployee(id);
        } catch (SQLException e) {
            return null;
        }
    }
}
