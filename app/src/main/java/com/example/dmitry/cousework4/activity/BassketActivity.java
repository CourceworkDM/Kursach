package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;

import java.util.Calendar;

/**
 * Created by Mary on 23.11.2017.
 */

public class BassketActivity extends Activity {
    ListView listView;
    public DBHelper DB;
    Button buttonClear;
    public DBHelper DB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_backet);

        buttonClear = findViewById(R.id.activity_clear);
        listView = findViewById(R.id.activity_listView);
        DB = new DBHelper(this, Contract.Basket.TABLE_NAME, null, 1);
        DB2 =  new DBHelper(this, Contract.Waste.TABLE_NAME, null, 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                DB.getBasket(Contract.Basket._ID + "> 0"));
        try {

            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder adb=new AlertDialog.Builder(BassketActivity.this);
                adb.setTitle("Удалить данные?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DB.delete_atBasket();
                    }});
                adb.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;

                final String strText = textView.getText().toString();
                String srtarr [] = strText.split(" ");
                AlertDialog.Builder adb=new AlertDialog.Builder(BassketActivity.this);
                adb.setTitle(" Добавить к расходам?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final Calendar c = Calendar.getInstance();
                        int Year = c.get(Calendar.YEAR);
                        int Month = c.get(Calendar.MONTH)+1;
                        int Day = c.get(Calendar.DAY_OF_MONTH);
                        final String date = String.valueOf(Year) + "/" + String.valueOf(Month) + "/" + String.valueOf(Day);
                        DB2.insert_cost(srtarr[0], srtarr[srtarr.length-1] , date );
                        DB.deleteBas(strText);
                        Method();
                    }});
                adb.show();
            }
        });

    }
    public void Method(){
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getBasket(Contract.Basket._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }
}
