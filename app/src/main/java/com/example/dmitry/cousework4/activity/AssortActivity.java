package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.model.models.Product;
import com.example.dmitry.cousework4.presenter.PresenterProducts;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;

public class AssortActivity extends Activity implements Iview<Product>{
    private static final String LOG_TAG ="AssortActivity" ;
    private Button button;
    private Button buttonComment;
    private final PresenterProducts presenter = new PresenterProducts();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assort);

        buttonComment = findViewById(R.id.activity_assort_buttonComment);
        listView = findViewById(R.id.activity_assort_listView);
        presenter.attachView(this);
    }

    @Override
    public void onReseived(List<Product> list) {
        ArrayAdapter<Product> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        Log.d(LOG_TAG, String.valueOf(list.size()));
        listView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        presenter.loadProductsFrom(getIntent().getIntExtra("id", -1));
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
