package edu.mob.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ConcurrentModificationException;

public class SQLTestActivity extends AppCompatActivity {

    public Button run;
    public TextView message;
    public ProgressBar progressBar;
    public Connection con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);

        run = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });
    }

    public class CheckLogin extends AsyncTask<String, String, String>
    {
        String z = "";
        Boolean isSuccess = false;
        String position1 = "";

        protected void onPreExecute()
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        protected void onPostExecute(String r)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(SQLTestActivity.this, r, Toast.LENGTH_LONG).show();
            if(isSuccess)
            {
                message = (TextView) findViewById(R.id.textView2);
                message.setText(position1);
            }
        }



        @Override
        protected String doInBackground(String... strings) {
            try
            {
                con = connectionclass();
                if(con == null)
                {
                    z = "Check Your Internet Access";
                }
                else
                {
                    String query = "select * from employees";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next())
                    {
                        position1 = rs.getString("position");
                        z = "query successful";
                        isSuccess = true;
                        con.close();
                    }
                    else
                    {
                        z = "invalid query";
                        isSuccess = false;
                    }
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
                z = ex.getMessage();
                Log.d("sql error", z);
            }
            return z;
        }

        @SuppressLint("NewApi")
        public Connection connectionclass()
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
}