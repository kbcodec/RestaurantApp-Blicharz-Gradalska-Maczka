package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Klasa ScheduleEmployeeService jest klasą odpowiedzialną za łączenie się i dodanie danych do bazy danych dotyczących Schedule_Employee
 */
public class ScheduleEmployeeService {
    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    /**
     * Pozwala na dodanie pracownika do planu pracy
     * Wykorzystuje PreparedStatement do wstawienia odpowiednich wartości do tabeli Schedule_Employee.
     * @param scheduleId - kolumna id_Schedule_Work w bazie danych
     * @param employeeId - kolumna id_employeew bazie danych
     * @throws SQLException
     */
    public void addScheduleEmployee(int scheduleId, int employeeId) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Schedule_Employee (id_Schedule_Work, id_employee) VALUES (?, ?)");
        stmt.setInt(1, scheduleId);
        stmt.setInt(2, employeeId);
        stmt.executeUpdate();
    }
}
