package com.example.food_firebase.model;

import java.util.List;

public class Receipt {
    public static int count = 0;
    private String id0fRecript;
    private String id0fCustomer;
    private List<Cart> cartList;
    private String total;
    private String date;

    public Receipt() {
    }

    public Receipt(String id0fRecript, String id0fCustomer, List<Cart> cartList, String total, String day) {
        this.id0fRecript = id0fRecript;
        this.id0fCustomer = id0fCustomer;
        this.cartList = cartList;
        this.total = total;
        this.date = day;
    }

    public String getDay() {
        return date;
    }

    public void setDay(String day) {
        this.date = day;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getId0fRecript() {
        return id0fRecript;
    }

    public void setId0fRecript(String id0fRecript) {
        this.id0fRecript = id0fRecript;
    }

    public String getId0fCustomer() {
        return id0fCustomer;
    }

    public void setId0fCustomer(String id0fCustomer) {
        this.id0fCustomer = id0fCustomer;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
