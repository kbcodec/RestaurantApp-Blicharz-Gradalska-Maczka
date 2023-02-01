package com.example.projecttest4.models;

/**
 * Klasa ChosenDish to model danych, który opisuje wybrane danie.
 * Zawiera ona informacje takie jak id, nazwę, wartość do polecenia,
 * notatki dotyczące gotowania, cenę, informację czy jest wegańskie i bez laktozy oraz adres URL zdjęcia.
 * Klasa posiada także metody do pobierania i zwracania poszczególnych informacji jako ciągów znaków.
 */
public class ChosenDish {
    private int id;
    private String name;
    private String worthToRecommend;
    private String cookingNotes;
    private double price;
    private int isVegan;
    private int isLactoseFree;
    private String imgUrl;

    public ChosenDish(int id, String name, String worthToRecommend, String cookingNotes, double price, int isVegan, int isLactoseFree, String imgUrl) {
        this.id = id;
        this.name = name;
        this.worthToRecommend = worthToRecommend;
        this.cookingNotes = cookingNotes;
        this.price = price;
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
