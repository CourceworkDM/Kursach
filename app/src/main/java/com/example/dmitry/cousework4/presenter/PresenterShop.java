package com.example.dmitry.cousework4.presenter;

import android.util.Log;

import com.example.dmitry.cousework4.model.models.Shop;
import com.example.dmitry.cousework4.model.repository.ShopsRepository;
import com.example.dmitry.cousework4.view.Iview;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dmitry on 12.11.17.
 */

public class PresenterShop{

    private static final String LOG_TAG = "PresenterShop";

    private Iview<Shop> view;

    private final ShopsRepository repository = new ShopsRepository();

    public void attachView(Iview<Shop> view) {
        this.view = view;
    }

    public void loadShops() {
        repository.getDataFromServer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shop_one -> {
                    if (view != null) {
                        //Log.d(LOG_TAG, shop_one.toString());
                        view.onReseived(shop_one);
                    }
                        }, throwable  -> {
                            Log.e(LOG_TAG, throwable.getMessage());

                            if (view == null) {
                                Log.e(LOG_TAG, "ERROR: variable #view# is null");
                            }
                            else {
                                view.onReseived(null);
                            }
                        }
                );
    }

    public void detachView() {
        view = null;
    }
}
