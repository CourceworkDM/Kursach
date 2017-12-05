package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;
import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.presenter.PresenterComments;
import com.example.dmitry.cousework4.view.ISuccess;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;

/**
 * Created by Mary on 25.11.2017.
 */

public class ActivityMycomment extends Activity implements Iview<Comment>, ISuccess {
    private static final String LOG_TAG = "ActivityMycomment";
    private ListView listView;
    private ArrayAdapter<Comment> adapter;
    private final PresenterComments presenter = new PresenterComments();
    private int item = 0; //для адаптера
    DBHelper DB;
    Comment currentComment;
    Comment newComment;

    String delete = "delete";
    String edit = "edit";
    String userMode = "";
    enum Mode  {delete, edit}
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomment);
        listView = findViewById(R.id.list);
        DB = new DBHelper(this, Contract.Comment.TABLE_NAME, null, 1);
        //редактирование
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            currentComment = (Comment)listView.getAdapter().getItem(i);
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
        Log.d(LOG_TAG, String.valueOf(isSuccess));
        String message = "";
        if (isSuccess) {
            if (userMode.equals(Mode.delete.name())) {
                final String strText = listView.getAdapter().getItem(item).toString();
                String strarr [] = strText.split("\n");
                st = strarr[1];
                DB.delete_comment(String.valueOf(st));
                adapter.remove((Comment)listView.getAdapter().getItem(item));

                message = "Успешно удалено";
            }
            else {
                DB.delete_comment(currentComment.getCommentLine());
                DB.insert_comment(newComment.getCommentLine(),
                        String.valueOf(newComment.getId()),
                        String.valueOf(newComment.getRate()));
                message = "Редактирование успешно";
            }
            presenter.getLocalComments(this);
        }
        else {
            message = "Что-то пошло не так. Проверьте подключение к сети";
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

            newComment = new Comment();
            newComment.setId(Integer.valueOf(id));
            newComment.setCommentLine(newCommentLine);
            newComment.setRate(Integer.valueOf(newRate));
            newComment.setShopFK("3");//ни на что не влияет

            presenter.editComment(newComment);




            //тут надо обновлять список


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
