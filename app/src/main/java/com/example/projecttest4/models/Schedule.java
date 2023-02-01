package com.example.projecttest4.models;

import java.sql.Date;

/**
 * Klasa Schedule to model danych, który odwzorowuje table Schedule_Work z bazy danych
 * Przechowuje informacje o dacie i zmianie.
 */
public class Schedule {
    private Date date;
    private String shift;

    public Schedule(Date date, String shift) {
        this.date = date;
        this.shift = shift;
    }

    public Date getDate() {
        return date;
    }

    public String getShift() {
        return shift;
    }
}
