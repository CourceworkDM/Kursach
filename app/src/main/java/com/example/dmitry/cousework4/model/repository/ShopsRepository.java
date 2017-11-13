package com.example.dmitry.cousework4.model.repository;



import com.example.dmitry.cousework4.api.BaseResponse;
import com.example.dmitry.cousework4.api.IRestService;
import com.example.dmitry.cousework4.api.RestServiceProvider;
import com.example.dmitry.cousework4.model.models.Shop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by dmitry on 12.11.17.
 */

public class ShopsRepository {

    private IRestService restService = RestServiceProvider.newInstance().getiRestService();

    public Observable<List<Shop>> getDataFromServer() {
        return restService.getAllShop();

    }
}
