package com.example.projecttest4.DAL;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    Klasa reprezentuje połączenie z bazą danych za pomocą biblioteki JDBC
 */
public class DBRestaurantConnect {

    /*
        Metoda ustanawia połączenie z bazą danych i zwraca obiekt typu Connection
     */
    @SuppressLint("NewApi")
    public Connection connectToDB() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://bgmrestaurantserver.database.windows.net:1433;DatabaseName=RestaurantDB;user=restaurantadmin@bgmrestaurantserver;password=VbWdLRs4bAdG8$M;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
