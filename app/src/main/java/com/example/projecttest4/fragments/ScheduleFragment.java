package com.example.projecttest4.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.projecttest4.R;
import com.example.projecttest4.adapters.ScheduleAdapter;
import com.example.projecttest4.controllers.ScheduleController;
import com.example.projecttest4.controllers.ScheduleEmployeeController;
import com.example.projecttest4.controllers.ShiftsTypesController;
import com.example.projecttest4.controllers.UserController;
import com.example.projecttest4.models.Schedule;
import com.example.projecttest4.models.ShiftTypes;
import com.example.projecttest4.models.User;
import com.example.projecttest4.services.ScheduleEmployeeService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * Klasa tworzy fragment kalendarza, w którym użytkownik może wybrać datę,
 * wybrać pracownika z listy dostępnych i wybrać zmianę z listy dostępnych zmian.
 */
public class ScheduleFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private ImageButton calendarDialogButton;
    private TextView chosenDate;
    private Spinner chooseWorkerSpinner;
    private Spinner chooseShiftSpinner;
    private Button addToScheduleButton;
    private Date date;
    private int userId;
    private ArrayList<Schedule> mSchedules;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public ScheduleFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
    * Metoda inicjuje komponenty interfejsu użytkownika, takie jak przyciski, pola tekstowe i spinner.
    * Także jest wyświetlany adapter, który wyświetla wszystkie istniejące wpisy w kalendarzu.
    * @param inflater - inflater, który służy do wczytywania layoutu fragmentu.
    * @param container - kontener, do którego zostanie dodany fragment.
    * @param savedInstanceState - stan instancji, który może zostać wczytany przy ponownym tworzeniu fragmentu.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ViewFlipper viewFlipper = view.findViewById(R.id.change_view);

        calendarDialogButton = view.findViewById(R.id.chooseDateButton);
        chosenDate = view.findViewById(R.id.chooseDateTf);
        chooseWorkerSpinner = view.findViewById(R.id.chooseWorkerSpinner);
        chooseShiftSpinner = view.findViewById(R.id.chooseShiftSpinner);
        addToScheduleButton = view.findViewById(R.id.addToScheduleButton);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(view.getContext());

        ScheduleController sc = new ScheduleController();
        User loggedUser = new UserController().getUser(acct.getEmail());
        mSchedules = sc.getSchedulesForEmployee(loggedUser.getId());
        System.out.println(mSchedules);
        mRecyclerView = view.findViewById(R.id.scheduleRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mAdapter = new ScheduleAdapter(view.getContext(), mSchedules);
        mRecyclerView.setAdapter(mAdapter);




        int numOfView = adjustScheduleFragment(acct);
        viewFlipper.setDisplayedChild(numOfView);

        // set Spinner Workers
        Spinner chooseWorkerSpinner = view.findViewById(R.id.chooseWorkerSpinner);
        ArrayList<String> emails = null;
        try {
            emails = adjustListOfUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterWorkers = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, emails);
        adapterWorkers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseWorkerSpinner.setAdapter(adapterWorkers);

        // set Spinner Shift
        Spinner chooseShiftSpinner = view.findViewById(R.id.chooseShiftSpinner);
        ArrayList<String> shifts = null;
        try {
            shifts = adjustListOfShifts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterShifts = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, shifts);
        adapterShifts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseShiftSpinner.setAdapter(adapterShifts);

        calendarDialogButton.setOnClickListener(v -> showDatePickerDialog(v));

        addToScheduleButton.setOnClickListener(v -> {
            ScheduleEmployeeController sec = new ScheduleEmployeeController();
            User u = new UserController().getUser(String.valueOf(chooseWorkerSpinner.getSelectedItem()));
            if(date != null && chooseShiftSpinner.getSelectedItemPosition() != -1 && chooseWorkerSpinner.getSelectedItemPosition() != -1)
            {
                sc.addSchedule(date, chooseShiftSpinner.getSelectedItemPosition()+1);
                sec.addScheduleEmployee(sc.getLastId(), u.getId());
                Toast.makeText(view.getContext(), "Udało się dodać zmianę", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(view.getContext(), "Wybierz wszystkie pola", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    /**
     * Metoda wywołuje DatePickerDialog, który pozwala użytkownikowi wybrać datę z kalendarza.
     * @param v - widok
     */
    private void showDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                v.getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    /**
     * W metodzie następuje aktualizacja tekstu wyświetlającego wybraną datę.
     * @param view - obiekt typu "DatePicker", który jest wywoływany w momencie ustawiania daty.
     * @param year - rok, który został wybrany w kalendarzu.
     * @param month- miesiąc, który został wybrany w kalendarzu.
     * @param dayOfMonth  - dzień miesiąca, który został wybrany w kalendarzu.
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateString = year + "-" + month + 1 + "-" + dayOfMonth;
        date = Date.valueOf(dateString);
        chosenDate.setText(dateString);
    }


    /**
     * Metoda służy do określenia, który widok ma być wyświetlony w zależności od roli użytkownika w systemie
     * @param acct - dane konta googlowskiego
     */
    private int adjustScheduleFragment (GoogleSignInAccount acct) {
        UserController uc = new UserController();
        User userFetchByEmail = uc.getUser(acct.getEmail());
        userId = userFetchByEmail.getId();
        int userFetchByEmailPosition = userFetchByEmail.getPosition();

        int userPosition = 0;

        if(userFetchByEmailPosition == 2 || userFetchByEmailPosition == 3) { //COOK AND WAITER
            userPosition = 0;
        } else if (userFetchByEmailPosition == 1) { //BOSS
            userPosition = 1;
        }
        return userPosition;
    }

    /**
     * Metoda zwraca listę wszystkich adresów e-mail użytkowników.
     * @return emails - lista e-maili
     * @throws SQLException
     */
    private ArrayList<String> adjustListOfUsers() throws SQLException {
        UserController uc = new UserController();
        ArrayList<User> allUsers = uc.getUsers();

        ArrayList<String> emails = new ArrayList<>();

        for (User user : allUsers){
            emails.add(user.getEmail());
        }

        return emails;
    }

    /**
     * Metoda służy do przygotowania listy typów zmian w formie listy ciągów znaków.
     * @return shifts - lista zmian pracownikow
     * @throws SQLException
     */
    private ArrayList<String> adjustListOfShifts() throws SQLException {
        ShiftsTypesController sc = new ShiftsTypesController();
        ArrayList<ShiftTypes> allShifts = sc.getShiftsTypes();

        ArrayList<String> shifts = new ArrayList<>();

        for (ShiftTypes shift : allShifts){
            shifts.add(shift.getShiftHours());
        }

        return shifts;
    }



}