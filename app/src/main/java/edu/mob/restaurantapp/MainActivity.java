package edu.mob.restaurantapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;


    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        GetList(null);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        User userFromDBdependsOnGoogleEmail = null;
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                userFromDBdependsOnGoogleEmail = getUserFromDatabase(acct.getEmail());
            }

            //DATABASE ODCZYTANIE EMAILA I SPRAWDZENIE JAKA MA FUNKCJE, JEZELI NIE MA OSOBY W TABELI TO PRZENIES NA WIDOK DAN I WYSWIETL ALERT ZE NIE MA DOSTEPU


            if(userFromDBdependsOnGoogleEmail == null) {
                Toast.makeText(this, String.format("Nie znaleziono uzytkownika o adresie email: %s", acct.getEmail()), Toast.LENGTH_LONG).show();
                mGoogleSignInClient.signOut();
            }
            else {
                startActivity(new Intent(MainActivity.this, Class.forName("edu.mob.restaurantapp."+userFromDBdependsOnGoogleEmail.getPosition() + "MainActivity")));
            }





        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Message", e.toString());

        } catch (SQLException e) {
            Log.d("Message", e.toString() + "nie utworzono uzytkownika z bazy danych na podstawie email");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //ListItem
    SimpleAdapter ad;

    public void GetList(View v){
        ListView lstview = (ListView) findViewById(R.id.listDish);

        List<Map<String, String>> MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getList();

        String [] Fromw = {"Nazwa", "Cena", "Wegańskie", "Laktoza"};
        int [] Tow = {R.id.Name, R.id.Price, R.id.Vegan, R.id.Lactose};

        ad = new SimpleAdapter(MainActivity.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
        lstview.setAdapter(ad);
    }


    private User getUserFromDatabase (String googleEmail) throws SQLException {
        User dbUser = null;
        int idUser;
        String firstName;
        String lastName;
        String email;
        String position;

        Connection connection;

        ConnectionHelper conhc = new ConnectionHelper();
        connection = conhc.conclass();

        if(connection != null) {
            String query = "SELECT * FROM Employees WHERE email = '" + googleEmail + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while(resultSet.next()) {
                idUser = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                email = resultSet.getString(4);
                position = resultSet.getString(5);

                dbUser = new User(idUser, firstName, lastName, email, position);
                System.out.println(dbUser);
            }
            if(dbUser != null)
                return dbUser;
        } else {
            Log.d("Message", "Check connection");
        }
        return null;
    }



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
                    dtname.put("Name", resultSet.getString("nameDish"));
                    dtname.put("Price", resultSet.getString("price"));
                    dtname.put("Vegan", resultSet.getString("isVegan"));
                    dtname.put("Lactose", resultSet.getString("isLactoseFree"));
                    data.add(dtname);
                }
                ConnectionResult = "Success";
                isSuccess = true;
                connect.close();
            }
            else {
                ConnectionResult = "Failed";
            }
        } catch (android.database.SQLException throwables) {
            throwables.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


}