package com.example.food_firebase.model;

public class Client {
    String id,fname,lname,email,password,comfirmpassworn,address;

    public Client() {
    }

    public Client(String id, String fname, String lname, String email, String password, String comfirmpassworn, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.comfirmpassworn = comfirmpassworn;
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

    public String getComfirmpassworn() {
        return comfirmpassworn;
    }

    public void setComfirmpassworn(String comfirmpassworn) {
        this.comfirmpassworn = comfirmpassworn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
