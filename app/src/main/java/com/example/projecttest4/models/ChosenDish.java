package com.example.projecttest4.models;

public class ChosenDish {
    private int id;
    private String name;
    private int isVegan;
    private int isLactoseFree;
    private String imgUrl;


    public ChosenDish(int id, String name, int isVegan, int isLactoseFree, String imgUrl) {
        this.id = id;
        this.name = name;
        this.isVegan = isVegan;
        this.isLactoseFree = isLactoseFree;
        this.imgUrl = imgUrl;
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
    public String getImgUrl() {return imgUrl;}

}
