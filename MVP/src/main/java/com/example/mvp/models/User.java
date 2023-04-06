package com.example.mvp.models;

public class User {
    private String number, password, secondName, firstName, lastName, roleName;

    public User(String number, String password, String secondName, String firstName, String lastName, String roleName) {
        this.number = number;
        this.password = password;
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
