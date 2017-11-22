package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dmitry.cousework4.R;
//import com.example.dmitry.cousework4.models.models.Shop;
import com.example.dmitry.cousework4.model.models.Shop;
import com.example.dmitry.cousework4.presenter.PresenterShop;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;


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
        listView.setOnItemClickListener((adapterView, view, i, l) -> onClickItemListView(i));
        presenterShop.attachView(this);
    }

    @Override
    protected void onStart() {
        presenterShop.loadShops();
        super.onStart();

    }

    @Override
    public void onReseived(List<Shop> list) {
        ArrayAdapter<Shop> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        Log.d(LOG_TAG, String.valueOf(adapter.getCount()));
    }



    @Override
    protected void onDestroy() {
        presenterShop.detachView();
        super.onDestroy();
    }

    private void onClickItemListView(int position) {
        int id = ((Shop) listView.getItemAtPosition(position)).getId();//получаем id выбранного магазина
        Intent intent = new Intent(ShopsActivity.this, AssortActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}


