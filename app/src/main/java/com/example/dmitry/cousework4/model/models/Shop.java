package com.example.dmitry.cousework4.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitry on 11.11.17.
 */

public class Shop {

    public Shop() { }

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("adress")
    private String adress;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return name + " на " + adress;
    }
}
