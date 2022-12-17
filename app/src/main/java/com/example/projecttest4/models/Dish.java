package com.example.projecttest4.models;

public class Dish {
    private int id;
    private String name;
    private int isVegan;
    private int isLactoseFree;

    public Dish(int id, String name, int isVegan, int isLactoseFree) {
        this.id = id;
        this.name = name;
        this.isVegan = isVegan;
        this.isLactoseFree = isLactoseFree;
    }

    public Dish() {
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

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isVegan=" + isVegan +
                ", isLactoseFree=" + isLactoseFree +
                '}';
    }
}
