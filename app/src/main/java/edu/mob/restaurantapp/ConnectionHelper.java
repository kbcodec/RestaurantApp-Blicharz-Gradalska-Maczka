package edu.mob.restaurantapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    public Connection con;


    @SuppressLint("NewApi")
    public Connection conclass()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://bgmrestaurantserver.database.windows.net:1433;DatabaseName=RestaurantDB;user=restaurantadmin@bgmrestaurantserver;password=VbWdLRs4bAdG8$M;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=request;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e) {
            Log.e("error here 1 : ", e.getMessage());
        } catch (SQLException e) {
            Log.e("error here 2 : ", e.getMessage());
        } catch(Exception e) {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }


}
