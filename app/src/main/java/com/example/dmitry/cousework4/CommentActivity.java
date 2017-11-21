package com.example.dmitry.cousework4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Mary on 21.11.2017.
 */

public class CommentActivity extends Activity {
    private Button button;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        button = (Button) findViewById(R.id.button);
        listView = (ListView)findViewById(R.id.listView);

    }
}
