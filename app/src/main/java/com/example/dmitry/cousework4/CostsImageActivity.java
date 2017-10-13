package com.example.dmitry.cousework4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;


/**
 * Created by Mary on 22.03.2017.
 */

public class CostsImageActivity extends Activity {
    ListView listView;
    public DBHelper DB;
    Button button14;
    EditText editText3;
   // String date = getIntent().getExtras().getString("date");
    //String date = getIntent().getStringExtra("date");
   String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_costs_image);
        date = getIntent().getExtras().getString("date");
        DB = new DBHelper(this, Contract.Waste.TABLE_NAME, null, 1);
        //DB.insert_cost_test();
        listView = (ListView)findViewById(R.id.activity_costs_image_listView_costs);
        button14 = (Button)findViewById(R.id.activity_costs_image_button_clear);
        editText3 = (EditText)findViewById(R.id.editText3);
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getCost(Contract.Waste.COLUMN_NAME_2 + " = '" + date + "'"));

            listView.setAdapter(adapter);
            editText3.setText("Расходы: " + DB.get_mon(Contract.Waste.COLUMN_NAME_2 + " = '" + date + "'") + " р." );

        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position, long id) {
//                TextView textView = (TextView) itemClicked;
//
//                final String strText = textView.getText().toString(); // получаем текст нажатого элемента
//                final String[] str = strText.split(" ");
//               // Toast.makeText(getApplicationContext(),str[0] , Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder adb=new AlertDialog.Builder(CostsImageActivity.this);
//                adb.setTitle("Удалить элемент?");
//
//                adb.setNegativeButton("Отмена     ", null);
//                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        DB.delete_cost(str[0]);
//                        updateAdapter();
//                    }});
//                adb.show();
//                return true;
//            }
//        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder adb=new AlertDialog.Builder(CostsImageActivity.this);
                adb.setTitle("Удалить данные?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DB.delete_cost(date);
                        editText3.setText("");
                        Method();
                    }});
                adb.show();
            }
        });
    }
    public void Method(){
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getCost(Contract.Waste.COLUMN_NAME_2 + " = '" + date + "'"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }

}
