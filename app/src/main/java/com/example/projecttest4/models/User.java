package com.example.projecttest4.models;

/**
 * Klasa to model danych, który odwzorowuje table User z bazy danych
 * Posiada pola dla wszystkich kolumn w tej tabeli
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int position;

    public User() {
        this.id = -1;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.position = -1;
    }

    public User(int id, String firstName, String lastName, String email, int position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
