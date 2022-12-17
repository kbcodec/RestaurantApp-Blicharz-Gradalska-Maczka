package com.example.projecttest4.models;

public class ChosenDish {
    private int id;
    private String name;
    private int isVegan;
    private int isLactoseFree;

    public ChosenDish(int id, String name, int isVegan, int isLactoseFree) {
        this.id = id;
        this.name = name;
        this.isVegan = isVegan;
        this.isLactoseFree = isLactoseFree;
    }

    public ChosenDish() {

    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public int getIsVegan() {
        return isVegan;
    }

    public String getIsVeganString() {
        return String.valueOf(isVegan);
    }

    public int getIsLactoseFree() {
        return isLactoseFree;
    }

    public String getIsLactoseFreeString() {
        return String.valueOf(isLactoseFree);
    }
}
