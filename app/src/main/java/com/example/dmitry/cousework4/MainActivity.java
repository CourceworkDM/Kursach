package com.example.dmitry.cousework4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dmitry.cousework4.activity.BasketActivity;
import com.example.dmitry.cousework4.activity.ShopsActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_my_list;
    private Button button_my_notes;
    private Button button_shops;
    private Button button_costs;
    private Button button_basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_my_list = (Button) findViewById(R.id.mainActivityButtonMyLists);
        button_my_notes = (Button) findViewById(R.id.mainActivityButtonMyNotes);
        button_shops = (Button) findViewById(R.id.mainActivityButtonShops);
        button_costs = (Button) findViewById(R.id.mainActivityButtonCosts);
        button_basket = (Button) findViewById(R.id.mainActivityBasket);

        //так писать нельзя цвет. надо его выносить в colors.xml
        button_my_list.setBackgroundColor(Color.rgb(134,132,217));
        button_my_notes.setBackgroundColor(Color.rgb(134,132,217));
        button_shops.setBackgroundColor(Color.rgb(134,132,217));
        button_costs.setBackgroundColor(Color.rgb(134,132,217));
        button_basket.setBackgroundColor(Color.rgb(134,132,217));

        button_my_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, MyListsActivity.class);
                startActivity(intent);
            }
        });

        button_my_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
        button_shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, ShopsActivity.class);
                startActivity(intent);
            }
        });
        button_costs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, CostsCalendarActivity.class);
                startActivity(intent);
            }
        });
        button_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });
    }
}
