package com.diegor.bani.model;

import java.util.HashMap;

/**
 * Created by Gino Osahon on 04/03/2017.
 */

public class User {

    private String fullName;
    private String Telephone;
    private String email;
    private String Adres;
    private String photo;
    private HashMap<String, Object> timestampJoined;

    public User() {
    }

    /**
     * Use this constructor to create new User.
     * Takes user name, email and timestampJoined as params
     *
     * @param timestampJoined
     */
    public User(String mFullName, String mPhoto, String mEmail, HashMap<String, Object> timestampJoined) {
        this.fullName = mFullName;
        this.photo = mPhoto;
        this.email = mEmail;
        this.timestampJoined = timestampJoined;
    }

    public User(String mFullName, String mPhoneNo, String mEmail, String mAdres){

        if(mAdres.length() == 0){
            mAdres = "Adres";
        }

        if(mPhoneNo.length() == 0){
            mPhoneNo = "Telefoon";
        }

        this.fullName = mFullName;
        this.Telephone = mPhoneNo;
        this.email = mEmail;
        this.Adres = mAdres;
    }

    //getters
    public String getFullName() {
        return fullName;
    }
    public String getPhoto() {
        return photo;
    }
    public String getEmail() {
        return email;
    }
    public String getAdres() { return Adres; }
    public String getTelephone() { return Telephone; }
    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    //setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAdres(String Adres) {
        this.Adres = Adres;
    }
    public void setPhone(String Telephone) {
        this.Telephone = Telephone;
    }

    public void setTimestampJoined(HashMap<String, Object> timestampJoined) {
        this.timestampJoined = timestampJoined;
    }
}
