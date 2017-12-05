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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cousework4.MapsActivity;
import com.example.dmitry.cousework4.NotesActivity;
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                final String strText = textView.getText().toString();
                AlertDialog.Builder adb = new AlertDialog.Builder( ShopsActivity.this);
                adb.setTitle("Показать на карте?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShopsActivity.this, MapsActivity.class);
                        intent.putExtra("shop", strText);
                        startActivity(intent);
                    }
                });
                adb.show();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        presenterShop.loadShops();
        super.onStart();

    }

    @Override
    public void onReseived(List<Shop> list) {
        if (list == null) {
            Toast t = Toast.makeText(this, "Что-то пошло не так. Проверьте подключение к интернету",
                    Toast.LENGTH_SHORT);
            t.show();
            return;
        }
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


