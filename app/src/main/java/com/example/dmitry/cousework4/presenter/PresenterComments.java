package com.example.dmitry.cousework4.presenter;

import android.util.Log;

import com.example.dmitry.cousework4.model.models.Comment;
import com.example.dmitry.cousework4.model.repository.CommentsRepository;
import com.example.dmitry.cousework4.view.Iview;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dmitry on 22.11.17.
 */

public class PresenterComments {

    private static final String LOG_TAG = "PresenterComments";

    private Iview<Comment> view;

    private final CommentsRepository repository = new CommentsRepository();

    public void attachView(Iview<Comment> view) {
        this.view = view;
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

        repository.sendNewCommentToServer(newComment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newId -> newComment.setId(newId),
                        throwable -> Log.e(LOG_TAG, throwable.getMessage()));
    }

    public void detachView() {
        view = null;
    }

}
