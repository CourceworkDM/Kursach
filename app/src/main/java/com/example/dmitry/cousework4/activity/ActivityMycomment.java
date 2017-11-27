package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.presenter.PresenterComments;
import com.example.dmitry.cousework4.view.ISuccess;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;

/**
 * Created by Mary on 25.11.2017.
 */

public class ActivityMycomment extends Activity implements Iview<Comment>, ISuccess {
    private ListView listView;
    private ArrayAdapter<Comment> adapter;
    private final PresenterComments presenter = new PresenterComments();
    private int item = 0; //для адаптера

    String delete = "delete";
    String edit = "edit";
    String userMode = "";
    enum Mode  {delete, edit};

    private int mode = 0;  //1 - edit ; 2 - delete
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomment);
        listView = findViewById(R.id.list);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            //редактирование

        });

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            presenter.deleteComment((Comment)listView.getAdapter().getItem(i));
            item = i;
            userMode = Mode.delete.name();
            return false; });
        presenter.attachView(this);
        presenter.attachSuccessView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getLocalComments(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        presenter.detachSuccessView();
        super.onDestroy();
    }

    @Override
    public void onReseived(List<Comment> list) {
        try {
            adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Override
    public void onReseived(boolean isSuccess) {
        String message = "";
        if (isSuccess) {
            if (userMode == Mode.delete.name()) {
                adapter.remove((Comment)listView.getAdapter().getItem(item));
                message = "Успешно удалено";
            }
            else {
                //тут изменить элемент
            }
        }
        else {
            message = "Что-то пошло не так. Попробуйте позже";
        }
        Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        t.show();
    }
}
