package com.example.dmitry.cousework4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class AssortActivity extends Activity {
    private Button button;
    private Button buttonComment;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assort);
        button = (Button) findViewById(R.id.button);
        buttonComment = (Button) findViewById(R.id.button2);
        listView = (ListView)findViewById(R.id.listView);

    }
}
