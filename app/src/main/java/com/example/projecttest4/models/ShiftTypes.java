package com.example.projecttest4.models;

/**
 * Klasa ShiftTypes opisuje typy zmian. Ma dwa pola - identyfikator i godziny zmiany.
 */
public class ShiftTypes {
    private int id;
    private String shiftHours;

    public ShiftTypes() {
        this.id = -1;
        this.shiftHours = "";

    }

    public ShiftTypes(int id, String shiftHours) {
        this.id = id;
        this.shiftHours = shiftHours;
    }

    public int getId() {
        return id;
    }

    public String getShiftHours() {
        return shiftHours;
    }
}
