package com.example.dmitry.cousework4.model.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitry on 11.11.17.
 */

public class Comment {

    @SerializedName("id")
    private int id;

    @SerializedName("rate")
    private int rate;

    @SerializedName("commentLine")
    private String commentLine;

    @SerializedName("shopFK")
    private String shopFK;

    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public String getCommentLine() {
        return commentLine;
    }

    public String getShopFK() {
        return shopFK;
    }

    public void setShopFK(String shopFK) {
        this.shopFK = shopFK;
    }

    public void setCommentLine(String commentLine) {
        this.commentLine = commentLine;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Оценка: "+ rate + "\n" + commentLine;
    }

}
