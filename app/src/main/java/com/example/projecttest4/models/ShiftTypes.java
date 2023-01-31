package com.example.projecttest4.models;

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
