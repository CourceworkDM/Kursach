package com.example.dmitry.cousework4.api;

import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.model.models.Product;
import com.example.dmitry.cousework4.model.models.Shop;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dmitry on 11.11.17.
 */

public interface IRestService {

    String BASE_URL = "http://80.87.198.110/database/";

    @GET("getallshops")
    Observable<List<Shop>>getAllShop();

    @GET("get_products_from/{idShop}")
    Observable<List<Product>> getProductFromShop(@Path("idShop") int id);

    @GET("get_comments_from/{idShop}")
    Observable<List<Comment>> getCommentsFrom(@Path("idShop") int id);

    @POST("create_comment")
    Observable<Integer> createComment(@Body List<Comment> comment);

    @POST("update_comment")
    void updateComment(@Body Comment comment);

    @POST("delete_comment")
    void deleteComment(@Body Comment comment);


}
