package com.example.xiaomage.xingvoices.feature.main.comment;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class CommentPresenter extends BasePresenter<CommentContract.View, MainRepository> implements CommentContract.Presenter {

    public CommentPresenter(@NonNull MainRepository repository, @NonNull CommentContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
