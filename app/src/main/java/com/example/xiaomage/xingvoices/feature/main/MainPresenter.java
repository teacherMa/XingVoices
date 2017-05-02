package com.example.xiaomage.xingvoices.feature.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class MainPresenter extends BasePresenter<MainContract.View, MainRepository> implements MainContract.Presenter {

    public MainPresenter(@NonNull MainRepository repository, @NonNull MainContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
