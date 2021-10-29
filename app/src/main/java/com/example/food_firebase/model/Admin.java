package com.example.food_firebase.model;

public class Admin {
    String id;
    String fname;
    String lname;
    String email;
    String password;
    String cfpassword;
    String address;

    public Admin() {
    }

    public Admin(String id, String fname, String lname, String email, String password, String cfpassword, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cfpassword = cfpassword;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCfpassword() {
        return cfpassword;
    }

    public void setCfpassword(String cfpassword) {
        this.cfpassword = cfpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
