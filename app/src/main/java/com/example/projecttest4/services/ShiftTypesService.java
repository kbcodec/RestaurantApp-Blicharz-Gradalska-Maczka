package com.example.projecttest4.services;

import com.example.projecttest4.DAL.DBRestaurantConnect;
import com.example.projecttest4.models.ShiftTypes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Klasa ShiftTypesService jest klasą odpowiedzialną za łączenie się
 * i pobieranie danych z bazy danych dotyczących typów zmian pracowników
 */
public class ShiftTypesService {

    private Connection connection;
    private DBRestaurantConnect restaurantConnect = new DBRestaurantConnect();

    /**
     * Metoda wykonuje zapytanie do bazy danych, pobierające informacje o wszystkich rodzajach zmian.
     * Zwrócony jest wynik w postaci listy obiektów ShiftTypes, które zawierają informacje o identyfikatorze oraz nazwie rodzaju zmiany.
     * @return listOfAllShifts - lista obiektów ShiftTypes
     * @throws SQLException
     */
    public ArrayList<ShiftTypes> getAllShifts() throws SQLException {
        connection = restaurantConnect.connectToDB();
        String query = "SELECT * FROM Shift_Types";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ArrayList<ShiftTypes> listOfAllShifts = new ArrayList<>();
        while(rs.next()) {
            ShiftTypes newShiftTypes = new ShiftTypes(
                    rs.getInt(1),
                    rs.getString(2)
            );
            listOfAllShifts.add(newShiftTypes);
        }
        return listOfAllShifts;
    }
}
