package com.example.dmitry.cousework4.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitry on 11.11.17.
 */

public class BaseResponse<T> {

    @SerializedName("data")
    protected T data;

    public T getData() { return data; }
}
