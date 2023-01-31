package com.example.projecttest4.controllers;

import com.example.projecttest4.services.ScheduleEmployeeService;
import com.example.projecttest4.services.ScheduleService;

import java.sql.Date;
import java.sql.SQLException;

public class ScheduleEmployeeController {
    public void addScheduleEmployee(int scheduleId, int employeeId) {
        ScheduleEmployeeService ses = new ScheduleEmployeeService();
        try {
            ses.addScheduleEmployee(scheduleId, employeeId);
        } catch (SQLException e) {
            ses =null;
        }
    }
}
