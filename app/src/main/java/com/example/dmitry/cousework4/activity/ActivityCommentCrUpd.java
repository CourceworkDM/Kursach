package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmitry.cousework4.R;
import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;
import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.presenter.PresenterComments;
import com.example.dmitry.cousework4.view.Iview;

import java.util.List;

public class ActivityCommentCrUpd extends Activity implements Iview<Comment> {
    Button buttonToServer;
    public DBHelper DB;
    EditText etComment;
    EditText rateComment;
    private final PresenterComments presenter = new PresenterComments();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_cr_upd);
        DB = new DBHelper(this, Contract.Comment.TABLE_NAME, null, 1);
        buttonToServer = findViewById(R.id.activity_comment_cr_upd_button_send);
        buttonToServer.setOnClickListener((View view)-> sendToServer());
        etComment = findViewById(R.id.activity_comment_cr_upd_edittext);
        rateComment = findViewById(R.id.activity_comment_cr_upd_ratetext);
        presenter.attachView(this);
    }

    @Override
    public void onReseived(List<Comment> list) {
        Comment aloneComment = list.get(0);//он всё равно один придёт с сервера
        DB.insert_comment(aloneComment.getCommentLine(),
                String.valueOf(aloneComment.getId()),
                String.valueOf(aloneComment.getRate()));
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private void sendToServer() {
        String textcomment = etComment.getText().toString();
        //проверка данных
        if (presenter.checkRate(rateComment.getText().toString()).second) {
            int rate = presenter.checkRate(rateComment.getText().toString()).first;
            int id = getIntent().getIntExtra("id", -1);
            presenter.sendNewComment(textcomment, rate, id);

            Toast message = Toast.makeText(this,"Комментарий отправлен!", Toast.LENGTH_SHORT);
            message.show();
            this.finish();
        }
        else {
            Toast message = Toast.makeText(this,"Неправильная оценка! (Она должна быть в диапазоне от 1 до 5)",
                    Toast.LENGTH_SHORT);
            message.show();
            rateComment.setText("");
        }

    }
}
