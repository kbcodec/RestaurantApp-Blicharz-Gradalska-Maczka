package edu.mob.restaurantapp;


import android.database.SQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItem {

    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess = false;

    public List<Map<String,String>> getList(){
        List<Map<String,String>> data =null;
        data = new ArrayList<Map<String,String>>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.conclass();
            if(connect !=null){
                String query = "select * from Dishes";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    Map<String,String> dtname = new HashMap<String,String>();
                    dtname.put("Nazwa", resultSet.getString("nameDish"));
                    dtname.put("Cena", resultSet.getString("price"));
                    dtname.put("Wegańskie", resultSet.getString("isVegan"));
                    dtname.put("Laktoza", resultSet.getString("isLactoseFree"));
                    data.add(dtname);
                }
                ConnectionResult = "Success";
                isSuccess = true;
                connect.close();
            }
            else {
                ConnectionResult = "Failed";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}

