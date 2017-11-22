package com.example.dmitry.cousework4.presenter;

import android.util.Log;

import com.example.dmitry.cousework4.model.models.Product;
import com.example.dmitry.cousework4.model.repository.ProductsRepository;
import com.example.dmitry.cousework4.view.Iview;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dmitry on 22.11.17.
 */

public class PresenterProducts {
    private static final String LOG_TAG = "PresenterProduct";

    private Iview<Product> view;

    private final ProductsRepository repository = new ProductsRepository();

    public void attachView(Iview<Product> view) {
        this.view = view;
    }

    public void loadProductsFrom(int id) {
        repository.getProductFromShop(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                            if (view != null) {
                                //Log.d(LOG_TAG, shop_one.toString());
                                view.onReseived(products);
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
