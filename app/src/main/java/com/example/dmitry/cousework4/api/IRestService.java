package com.example.dmitry.cousework4.api;

import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.model.models.Product;
import com.example.dmitry.cousework4.model.models.Shop;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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

    @Multipart
    @POST("create_comment")
    Observable<Integer> createComment(@Part("comment") Comment comment);// не использовал @Body, так как сервер принимает Form

    @Multipart
    @POST("update_comment")
    Observable<Boolean> updateComment(@Part("comment") Comment comment);

    @Multipart
    @POST("delete_comment")
    Observable<Boolean> deleteComment(@Part("comment") Comment comment);


}
