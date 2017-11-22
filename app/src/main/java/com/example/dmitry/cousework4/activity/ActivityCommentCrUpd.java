package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.presenter.PresenterComments;
import com.example.dmitry.cousework4.presenter.PresenterProducts;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;

public class ActivityCommentCrUpd extends Activity implements Iview<Comment> {
    Button buttonToServer;
    EditText etComment;
    EditText rateComment;
    private final PresenterComments presenter = new PresenterComments();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_cr_upd);

        buttonToServer = findViewById(R.id.activity_comment_cr_upd_button_send);
        buttonToServer.setOnClickListener((View view)-> sendToServer());
        etComment = findViewById(R.id.activity_comment_cr_upd_edittext);
        rateComment = findViewById(R.id.activity_comment_cr_upd_ratetext);
        presenter.attachView(this);
    }

    @Override
    public void onReseived(List<Comment> list) {

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private void sendToServer() {
        String textcomment = etComment.getText().toString();
        int rate = Integer.valueOf(rateComment.getText().toString());
        int id = getIntent().getIntExtra("id", -1);
        presenter.sendNewComment(textcomment, rate, id);
    }
}
