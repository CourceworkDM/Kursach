package com.example.dmitry.cousework4.model.repository;

import com.example.dmitry.cousework4.api.IRestService;
import com.example.dmitry.cousework4.api.RestServiceProvider;
import com.example.dmitry.cousework4.model.models.Comment;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dmitry on 22.11.17.
 */

public class CommentsRepository {

    private IRestService restService = RestServiceProvider.newInstance().getiRestService();

    public Observable<List<Comment>> getCommentsFromShop(int id) {
        return restService.getCommentsFrom(id);
    }
}
