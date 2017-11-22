package com.example.dmitry.cousework4.presenter;

import android.util.Log;

import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.model.models.Product;
import com.example.dmitry.cousework4.model.repository.CommentsRepository;
import com.example.dmitry.cousework4.model.repository.ProductsRepository;
import com.example.dmitry.cousework4.view.Iview;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dmitry on 22.11.17.
 */

public class PresenterComments {

    private static final String LOG_TAG = "PresenterComments";

    private Iview<Comment> view;

    private final CommentsRepository repository = new CommentsRepository();

    public void attachView(Iview<Comment> view) {
        this.view = view;
    }

    public void loadProductsFrom(int id) {
        repository.getCommentsFromShop(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                            if (view != null) {
                                //Log.d(LOG_TAG, shop_one.toString());
                                view.onReseived(comments);
                            }
                        }, throwable  -> {
                            Log.e(LOG_TAG, throwable.getMessage());

                            if (view == null) {
                                Log.e(LOG_TAG, "ERROR: variable #view# is null");
                            }
                        }
                );
    }

    public void detachView() {
        view = null;
    }

}
