package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.presenter.PresenterComments;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;

/**
 * Created by Mary on 21.11.2017.
 */

public class CommentActivity extends Activity implements Iview<Comment>{
    private ListView listView;
    private FloatingActionButton addComment;
    private final PresenterComments presenter = new PresenterComments();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        listView = findViewById(R.id.activity_comment_listView);
        addComment = findViewById(R.id.activity_comment_floatingActionButton);
        addComment.setOnClickListener((View view) -> toCreateComment());
        presenter.attachView(this);
    }

    @Override
    protected void onStart() {
        presenter.loadCommentsFrom(getIntent().getIntExtra("id", -1));
        super.onStart();
    }

    @Override
    public void onReseived(List<Comment> list) {
        ArrayAdapter<Comment> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private void toCreateComment() {
        Intent intent = new Intent(CommentActivity.this, ActivityCommentCrUpd.class);
        intent.putExtra("id", getIntent().getIntExtra("id", -1));
        startActivity(intent);

    }
}
