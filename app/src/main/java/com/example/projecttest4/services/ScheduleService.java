package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleService {
    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    public void addSchedule(Date date, int type) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Schedule_Work (id_Shift_Types, work_date) VALUES (?, ?)");
        stmt.setDate(2, date);
        stmt.setInt(1, type);
        stmt.executeUpdate();
    }

    public int getLastId() throws SQLException {
        connection =  restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("SELECT TOP (1) id_Schedule_Work FROM Schedule_Work ORDER BY id_Schedule_Work DESC");
        ResultSet rs = stmt.executeQuery();
        int result = 999;
        while(rs.next()) {
            result = rs.getInt("id_Schedule_Work");
        }
        return result;
    }
}
