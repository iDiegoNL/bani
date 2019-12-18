package com.diegor.bani.utils;

public class User {
    public String username;
    public String email;
    public String adres;
    public String telephone;

    public User(){
        //default constructor
    }

    public User(String username, String email, String adres, String telephone) {
        this.username = username;
        this.email = email;
        this.adres = adres;
        this.telephone = telephone;
    }

}
