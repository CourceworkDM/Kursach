package com.example.dmitry.cousework4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class NotesActivity extends Activity {

    ListView listView;
    EditText editText5;
    ArrayAdapter<String> adapter;
    public DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notes);
        DB = new DBHelper(this, Contract.Note.TABLE_NAME, null, 1);
        editText5 = (EditText) findViewById(R.id.editText5);
        listView = (ListView)findViewById(R.id.list);
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getNote(Contract.Note._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }

        editText5.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        DB.insert(editText5.getText().toString());
                        Method();
                        editText5.setText("");
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
                AlertDialog.Builder adb = new AlertDialog.Builder(NotesActivity.this);
                adb.setTitle("Удалить элемент?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DB.delete(strText);
                        Method();
                    }
                });
                adb.show();
                return true;
            }
        });
    }

public void Method(){
    try {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                DB.getNote(Contract.Note._ID + "> 0"));
        listView.setAdapter(adapter);
    }
    catch (Exception ex) {
        Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
        t.show();
    }
}
}
