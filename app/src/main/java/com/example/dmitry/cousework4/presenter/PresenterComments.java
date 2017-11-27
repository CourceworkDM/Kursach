package com.example.dmitry.cousework4.presenter;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.example.dmitry.cousework4.database.Contract;
import com.example.dmitry.cousework4.database.DBHelper;
import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.model.repository.CommentsRepository;
import com.example.dmitry.cousework4.view.ISuccess;
import com.example.dmitry.cousework4.view.Iview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dmitry on 22.11.17.
 */

public class PresenterComments {
    private DBHelper DB;

    private static final String LOG_TAG = "PresenterComments";

    private Iview<Comment> view;

    private ISuccess viewSucces;

    private final CommentsRepository repository = new CommentsRepository();

    public void attachView(Iview<Comment> view) {
        this.view = view;
    }

    public void attachSuccessView(ISuccess view) {
        this.viewSucces = view;
    }

    public void loadCommentsFrom(int id) {
        repository.getCommentsFromShop(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comments -> {
                            if (view != null) {
                                view.onReseived(comments);
                            }
                        }, throwable  -> {
                            Log.e(LOG_TAG, throwable.getMessage());

                            if (view == null) {
                                Log.e(LOG_TAG, "ERROR: variable #view# is null");
                            }
                        }
                );
    }

    public void sendNewComment(String comment,int rate, int id) {

        Comment newComment = new Comment();
        newComment.setCommentLine(comment);
        newComment.setRate(rate);
        newComment.setShopFK(String.valueOf(id));//все равно это изменится сервером

        //после отправки сохраняется в локальную БД (для отображения комментариев, что оставил сам пользователь)
        repository.sendNewCommentToServer(newComment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newId -> {
                    List<Comment> aloneComment = new ArrayList<Comment>();
                    newComment.setId(newId);
                    aloneComment.add(newComment);
                    view.onReseived(aloneComment);
                    },
                        throwable -> Log.e(LOG_TAG, throwable.getMessage()));

    }

    public void deleteComment(Comment comment)
    {
        repository.deleteComment(comment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isSuccess ->viewSucces.onReseived(isSuccess),
                        throwable -> {
                            Log.e(LOG_TAG, throwable.getMessage());
                            viewSucces.onReseived(false);
                        });

    }

    public void detachView() {
        view = null;
    }

    public void detachSuccessView() {
        viewSucces = null;
    }

    public Pair<Integer, Boolean> checkRate(String rate) {
        Pair<Integer, Boolean> result;
        int rateCheck = 0;

        try {
            rateCheck = Integer.valueOf(rate);
        }
        catch (Exception ex) {
            return new Pair<>(rateCheck, false);
        }

        if (rateCheck > 0 && rateCheck < 6) {
            return new Pair<>(rateCheck, true);
        }
        return new Pair<>(rateCheck, false);
    }

    public void getLocalComments(Context context) {
        DB = new DBHelper(context, Contract.Comment.TABLE_NAME, null, 1);
        if (view != null) {
            view.onReseived(DB.getComment(Contract.Comment._ID + "> 0"));
        }
        else {
            Log.e(LOG_TAG, "View is null!");
        }


    }

}
