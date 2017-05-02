package com.example.xiaomage.xingvoices.feature.main.popular;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class PopularPresenter extends BasePresenter<PopularContract.View, MainRepository> implements PopularContract.Presenter {

    public PopularPresenter(@NonNull MainRepository repository, @NonNull PopularContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
