package edu.mob.restaurantapp;

public class User {
    private int idEmployee;
    private String firstName;
    private String lastName;
    private String email;
    private String position;

    public User(int idEmployee, String firstName, String lastName, String email, String position) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" +
                "idEmployee=" + idEmployee +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                '}';
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

    public String getPosition() {
        return position;
    }
}
