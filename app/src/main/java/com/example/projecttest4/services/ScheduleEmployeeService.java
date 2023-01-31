package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleEmployeeService {
    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    public void addScheduleEmployee(int scheduleId, int employeeId) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Schedule_Employee (id_Schedule_Work, id_employee) VALUES (?, ?)");
        stmt.setInt(1, scheduleId);
        stmt.setInt(2, employeeId);
        stmt.executeUpdate();
    }
}
