package com.example.dmitry.cousework4.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitry on 11.11.17.
 */

public class Product {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private float price;

    @SerializedName("shopFK")
    private int shopFK;

    @Override
    public String toString() {
        return name + "\n" + price + "p";
    }
}
