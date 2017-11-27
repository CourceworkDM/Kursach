package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.presenter.PresenterComments;

/**
 * Created by Mary on 25.11.2017.
 */

public class ActivityMycomment extends Activity {
    ListView listView;
    private final PresenterComments presenter = new PresenterComments();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomment);
        listView = findViewById(R.id.list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //мне вот это жутко не нравится, почему не использовалась модель Comment, а кастомная какая-то?
        //в итоге, работать будет, но выглядит костыльно
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, presenter.getLocalComments(this));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }


}
