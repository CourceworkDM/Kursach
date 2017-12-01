package com.example.dmitry.cousework4.activity;

import android.app.Activity;
import android.content.Intent;
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
    private Button buttonToServer;
    private boolean isEditMode = false;
    public DBHelper DB;
    private EditText etComment;
    private EditText rateComment;
    private final PresenterComments presenter = new PresenterComments();

    //для редактирования в качестве буфера
    private Comment buferComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_cr_upd);

        DB = new DBHelper(this, Contract.Comment.TABLE_NAME, null, 1);

        buttonToServer = findViewById(R.id.activity_comment_cr_upd_button_send);
        buttonToServer.setOnClickListener((View view) -> sendToServer());

        etComment = findViewById(R.id.activity_comment_cr_upd_edittext);
        rateComment = findViewById(R.id.activity_comment_cr_upd_ratetext);

        presenter.attachView(this);

        //если режим редактирования, вызываем editMode
        Object objOfIntent = getIntent().getExtras().get("mode");
        if (objOfIntent != null && objOfIntent.toString().equals("edit")) {
            editModeUI();
        }
    }

    @Override
    public void onReseived(List<Comment> list) {
        Comment aloneComment = list.get(0);//комментарий всё равно один придёт с сервера
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
        //если редактирование
        if (isEditMode) {
            sendToServerEditing();
        } else {//если добавление
            sendToServerAdding();
        }
    }

    private void sendToServerEditing() {
        //установим новые значения
        buferComment.setCommentLine(etComment.getText().toString());
        buferComment.setRate(Integer.valueOf(rateComment.getText().toString()));

        //и передадим обратно в пред активити после проверки
        if (presenter.checkRate(rateComment.getText().toString()).second) {
            Intent data = new Intent();
            String st = getIntent().getExtras().get("commentLine").toString();
            data.putExtra("commentLine", buferComment.getCommentLine());
            data.putExtra("id", String.valueOf(buferComment.getId()));
            data.putExtra("rate", String.valueOf(buferComment.getRate()));
            setResult(RESULT_OK, data);
            DB.delete_comment(st);
            DB.insert_comment(buferComment.getCommentLine(),
                    String.valueOf(buferComment.getId()),
                    String.valueOf(buferComment.getRate()));
            this.finish();
        } else {
            Toast t = Toast.makeText(this, "Неправильная оценка! (Она должна быть в диапазоне от 1 до 5)",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }

    private void sendToServerAdding() {
        String textcomment = etComment.getText().toString();

        //проверка данных и отправка на сервер
        if (presenter.checkRate(rateComment.getText().toString()).second) {
            int rate = presenter.checkRate(rateComment.getText().toString()).first;
            int foreign_id = getIntent().getIntExtra("id", -1);//это id магазина!
            presenter.sendNewComment(textcomment, rate, foreign_id);

            Toast message = Toast.makeText(this, "Комментарий отправлен!", Toast.LENGTH_SHORT);
            message.show();
            this.finish();
        } else {
            Toast message = Toast.makeText(this, "Неправильная оценка! (Она должна быть в диапазоне от 1 до 5)",
                    Toast.LENGTH_SHORT);
            message.show();
            rateComment.setText("");
        }

    }

    public void editModeUI() {
        buferComment = new Comment();
        buferComment.setId(Integer.valueOf(getIntent().getExtras().get("id").toString()));//это id комментария
        buferComment.setRate(Integer.valueOf(getIntent().getExtras().get("rate").toString()));
        buferComment.setCommentLine(getIntent().getExtras().get("commentLine").toString());

        etComment.setText(buferComment.getCommentLine());
        rateComment.setText(String.valueOf(buferComment.getRate()));
        this.isEditMode = true;
    }
}
