package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.content.Intent;
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

        //редактирование
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Comment currentComment = (Comment)listView.getAdapter().getItem(i);
            //presenter.editComment(currentComment);
            item = i;
            startEditActivity(currentComment);
        });

        //удаление
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
                adapter.remove(adapter.getItem(item+1));//так как вставили уже новый коммент
            }
        }
        else {
            message = "Что-то пошло не так. Попробуйте позже";
        }
        Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            String newCommentLine = data.getStringExtra("commentLine");
            String newRate = data.getStringExtra("rate");
            String id = data.getStringExtra("id");
            Comment newComment = new Comment();
            newComment.setId(Integer.valueOf(id));
            newComment.setCommentLine(newCommentLine);
            newComment.setRate(Integer.valueOf(newRate));
            newComment.setShopFK("3");
            presenter.editComment(newComment);
            adapter.insert(newComment,item);

        }
        catch (Exception ex) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startEditActivity(Comment currentComment) {
        userMode = Mode.edit.name();
        Intent intent = new Intent(ActivityMycomment.this, ActivityCommentCrUpd.class);
        intent.putExtra("mode", Mode.edit);
        intent.putExtra("commentLine", currentComment.getCommentLine());
        intent.putExtra("rate", currentComment.getRate());
        intent.putExtra("id", currentComment.getId());
        startActivityForResult(intent,1);

    }
}