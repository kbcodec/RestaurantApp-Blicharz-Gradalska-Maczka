package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.controllers.TimeController;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasa TimeService jest klasą odpowiedzialną za łączenie się i pobieranie danych z bazy danych dotyczących czasu pracy - tabela Employment
 * Zawiera metody związane z zapisywaniem i pobieraniem informacji o godzinach pracy w restauracji.
 */
public class TimeService {

    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    /**
     * Metoda służy do zapisywania informacji o godzinach pracy w bazie danych
     * @param hours - czas pracy
     * @throws SQLException
     */
    public void saveHours (String hours) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Working_Hours (hours) VALUES(?)");
        stmt.setString(1, hours);
        stmt.executeUpdate();
    }

    /**
     * Metoda służy do zapisywania informacji o zatrudnieniu pracownika w danej liczbie godzin.
     * @param id_hours - dane do kolumny id_working_Hours
     * @param emply_id - dane do kolumny id_employee
     * @throws SQLException
     */
    public void saveEmployment (int id_hours, int emply_id) throws SQLException {
        connection = restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Employment (id_working_Hours, id_employee) VALUES(?,?)");

        stmt.setInt(1, id_hours);
        stmt.setInt(2, emply_id);
        stmt.executeUpdate();
    }


    /**
     * Metoda służy do pobierania ostatniego id zapisanego rekordu w bazie danych w tabeli Working_Hours
     * @return result - ostatnie id w Working_Hours
     * @throws SQLException
     */
    public int getLastId() throws SQLException {
        connection =  restaurantConnect.connectToDB();
        PreparedStatement stmt = connection.prepareStatement("SELECT IDENT_CURRENT('Working_Hours')");
        ResultSet rs = stmt.executeQuery();
        int result = 999;
        while(rs.next()) {
            result = rs.getInt(1);
        }
        return result;
    }

}
