package com.example.dmitry.cousework4.api;

import com.example.dmitry.cousework4.model.models.Shop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by dmitry on 11.11.17.
 */

public interface IRestService {

    String BASE_URL = "http://80.87.198.110/database/";

    @GET("getallshops")
    Observable<List<Shop>>getAllShop();


}
