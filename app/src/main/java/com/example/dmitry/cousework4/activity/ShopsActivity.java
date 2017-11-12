package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cousework4.AssortActivity;
import com.example.dmitry.cousework4.MapsActivity;
import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;
//import com.example.dmitry.cousework4.models.models.Shop;
import com.example.dmitry.cousework4.model.models.Shop;
import com.example.dmitry.cousework4.model.repository.ShopsRepository;
import com.example.dmitry.cousework4.presenter.PresenterShop;
import com.example.dmitry.cousework4.view.Iview;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Mary on 21.03.2017.
 */

public class ShopsActivity extends Activity implements Iview<Shop>{

    private static final String LOG_TAG = "ShopsActivity";
    private final PresenterShop presenterShop = new PresenterShop();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        listView = findViewById(R.id.activity_shops_listView);
        presenterShop.attachView(this);
    }

    @Override
    protected void onStart() {
        presenterShop.loadShops();
        super.onStart();

    }

    @Override
    public void onReseived(List<Shop> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                presenterShop.filterShops(list));
        listView.setAdapter(adapter);
        Log.d(LOG_TAG, String.valueOf(adapter.getCount()));

    }



    @Override
    protected void onDestroy() {
        presenterShop.detachView();
        super.onDestroy();
    }
}


