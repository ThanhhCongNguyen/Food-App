package com.example.food_firebase.model;

import com.example.food_firebase.model.Cart;

import java.util.List;

public class shopping_cart {
    private String id_cart;
    private String id_customer;
    private List<Cart> cartList;

    public shopping_cart(String id_cart, String id_customer, List<Cart> cartList) {
        this.id_cart = id_cart;
        this.id_customer = id_customer;
        this.cartList = cartList;
    }

    public String getId_cart() {
        return id_cart;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
