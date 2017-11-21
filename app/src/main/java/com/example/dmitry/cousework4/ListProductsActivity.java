package com.example.dmitry.cousework4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;

import java.util.Calendar;



/**
 * Created by Mary on 22.03.2017.
 */

public class ListProductsActivity extends Activity {

    EditText editText4;

    private static final int CM_DELETE_ID = 1;
    ListView listView;
    Button button8;
    public DBHelper DB;
    public DBHelper DB1;
    public DBHelper DB2;

String list;
    //String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_products);
         final String DATABASE_NAME = "database.db";
         final String LOG_TAG = DBHelper.class.getSimpleName();
        String s = "Список";
        String h = "На неделю";
        String r = "На выходные";
        String d = "Для праздника";
        list = getIntent().getExtras().getString("list");
        if( list.equals(s)) {
            DB = new DBHelper(this, Contract.Entry.TABLE_NAME, null, 1);
        }
        else if( list.equals(h)) {
            DB = new DBHelper(this, Contract.Prod.TABLE_NAME, null, 1);
        }
        else if( list.equals(r)) {
            DB = new DBHelper(this, Contract.Pro.TABLE_NAME, null, 1);
        }
        else if( list.equals(d)) {
            DB = new DBHelper(this, Contract.Pr.TABLE_NAME, null, 1);
        }
       else if( !list.equals(s)&!list.equals(h)&!list.equals(r)&!list.equals(d)) {
            DB = new DBHelper(this, Contract.Space.TABLE_NAME, null, 1);
        }
        DB1 = new DBHelper(this, Contract.Waste.TABLE_NAME, null, 1);
        DB2 = new DBHelper(this, Contract.Price.TABLE_NAME, null, 1);
        editText4 = (EditText) findViewById(R.id.editText4);
        listView = (ListView)findViewById(R.id.activity_costs_image_listView_costs);
        button8 = (Button) findViewById(R.id.button8);
        button8.setText(list);
       // DB2.insert_p();
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getTimeDoing(Contract.Entry._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }

        editText4.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        DB2.insert_price(editText4.getText().toString(), Integer.toString(10+(int)(Math.random()*200)));
                        DB.insertInDB(editText4.getText().toString());
                        Method();
                        editText4.setText("");
                        return true;
                    }
                return false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
               final String strText = textView.getText().toString();
                AlertDialog.Builder adb=new AlertDialog.Builder(ListProductsActivity.this);
                adb.setTitle("Удалить элемент?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DB.deleteFromDb(strText);
                        Method();
                    }});
                adb.show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;

                 final String strText = textView.getText().toString();
                final String price = DB2.getPrice(Contract.Price.COLUMN_NAME + " = '" + strText + "'");
                AlertDialog.Builder adb=new AlertDialog.Builder(ListProductsActivity.this);
                adb.setTitle(" Добавить к расходам?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final Calendar c = Calendar.getInstance();
                        int Year = c.get(Calendar.YEAR);
                        int Month = c.get(Calendar.MONTH)+1;
                        int Day = c.get(Calendar.DAY_OF_MONTH);
                        final String date = String.valueOf(Year) + "/" + String.valueOf(Month) + "/" + String.valueOf(Day);
                        DB1.insert_cost(strText, price , date );
                    }});
                adb.show();
            }
        });
    }
    public void Method(){
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getTimeDoing(Contract.Entry._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }


}
