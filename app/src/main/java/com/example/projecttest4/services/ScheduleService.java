package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.models.Schedule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Schedule> getSchedulesForEmployee(int id) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("select se.id_employee, sw.work_date, st.shift_hours from Schedule_Employee se inner join Schedule_work sw on se.id_Schedule_Work = sw.id_Schedule_Work inner join Shift_Types st on sw.id_Shift_Types = st.id_Shift_Types where se.id_employee = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Schedule> result = new ArrayList<>();
        while(rs.next()) {
            Schedule listedSchedule = new Schedule(rs.getDate(2), rs.getString(3));
            result.add(listedSchedule);
        }
        return result;
    }
}
