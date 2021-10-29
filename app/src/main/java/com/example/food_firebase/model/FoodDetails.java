package com.example.food_firebase.model;

import java.io.Serializable;

public class FoodDetails implements Serializable {

    private String Dishes,Quantity,Price,Description,ImageURL,RandomUID,ChefId;

    public FoodDetails() {
    }

    // Alt+insert


    public FoodDetails(String dishes, String quantity, String price, String description, String imageURL, String randomUID, String chefId) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        ChefId = chefId;
    }

    public String getDishes() {
        return Dishes;
    }

    public void setDishes(String dishes) {
        Dishes = dishes;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getChefId() {
        return ChefId;
    }

    public void setChefId(String chefId) {
        ChefId = chefId;
    }
}