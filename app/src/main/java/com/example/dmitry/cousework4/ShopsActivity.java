package com.example.dmitry.cousework4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;


/**
 * Created by Mary on 21.03.2017.
 */

public class ShopsActivity extends Activity {

    EditText editTextShopName;
    ArrayAdapter<String> adapter;
    ListView listView;
    public DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shops);
        DB = new DBHelper(this, Contract.Shop.TABLE_NAME, null, 1);
        editTextShopName = (EditText) findViewById(R.id.editText6);
        listView = (ListView)findViewById(R.id.activity_costs_image_listView_costs);
        try {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getShop(Contract.Shop._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
//        editTextShopName.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // TODO Auto-generated method stub
//                if (event.getAction() == KeyEvent.ACTION_DOWN)
//                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                        DB.insert_s(editTextShopName.getText().toString());
//                        updateAdapter();
//                        editTextShopName.setText("");
//                        return true;
//                    }
//                return false;
//            }
//        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                final String strText = textView.getText().toString(); // текст нажатого элемента
                AlertDialog.Builder adb=new AlertDialog.Builder(ShopsActivity.this);
                adb.setTitle("Показать ассортимент магазина?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //DB.delete_s(strText);
                        Intent intent = new Intent(ShopsActivity.this, AssortActivity.class);
                        startActivity(intent);
                        updateAdapter();
                    }});
                adb.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                final String strText = textView.getText().toString(); // текст нажатого элемента
                AlertDialog.Builder adb=new AlertDialog.Builder(ShopsActivity.this);
                adb.setTitle("Показать на карте?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShopsActivity.this, MapsActivity.class);
                        intent.putExtra("shop", strText);
                        startActivity(intent);
                    }});
                adb.show();
            }
        });
    }
    public void updateAdapter(){
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getShop(Contract.Shop._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }
}


