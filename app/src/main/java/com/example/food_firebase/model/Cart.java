package com.example.food_firebase.model;

public class Cart {
    private String idOfCart, ChefId,DishName,DishQuantity,Price,Totalprice,RamdomId;

    public Cart() {
    }

    public Cart(String idOfCart, String chefId, String dishName, String dishQuantity, String price, String totalprice, String ramdomId) {
        this.idOfCart = idOfCart;
        ChefId = chefId;
        DishName = dishName;
        DishQuantity = dishQuantity;
        Price = price;
        Totalprice = totalprice;
        RamdomId = ramdomId;
    }

    public String getIdOfCart() {
        return idOfCart;
    }

    public void setIdOfCart(String idOfCart) {
        this.idOfCart = idOfCart;
    }

    public String getChefId() {
        return ChefId;
    }

    public void setChefId(String chefId) {
        ChefId = chefId;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public String getDishQuantity() {
        return DishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        DishQuantity = dishQuantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        Totalprice = totalprice;
    }

    public String getRamdomId() {
        return RamdomId;
    }

    public void setRamdomId(String ramdomId) {
        RamdomId = ramdomId;
    }
}
