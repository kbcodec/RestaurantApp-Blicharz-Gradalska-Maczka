package com.example.projecttest4.models;

public class Dish {
    private int id;
    private String name;
    private String worthToRecommend;
    private String cookingNotes;
    private double price;
    private int isVegan;
    private int isLactoseFree;
    private String imgUrl;

    public Dish(int id, String name, String worthToRecommend, String cookingNotes, double price, int isVegan, int isLactoseFree, String imgUrl) {
        this.id = id;
        this.name = name;
        this.worthToRecommend = worthToRecommend;
        this.cookingNotes = cookingNotes;
        this.price = price;
        this.isVegan = isVegan;
        this.isLactoseFree = isLactoseFree;
        this.imgUrl = imgUrl;
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

    public String getWorthToRecommend() {return worthToRecommend;}

    public String getCookingNotes() {return cookingNotes;}

    public double getPrice() {return price;}
    public String getPriceString() {return String.valueOf(price);}

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
