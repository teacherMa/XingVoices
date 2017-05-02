package com.example.xiaomage.xingvoices.feature.main.follow;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class FollowPresenter extends BasePresenter<FollowContract.View, MainRepository> implements FollowContract.Presenter {

    public FollowPresenter(@NonNull MainRepository repository, @NonNull FollowContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
