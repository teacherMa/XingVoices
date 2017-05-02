package com.example.xiaomage.xingvoices.feature.main.collection;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class CollectionPresenter extends BasePresenter<CollectionContract.View, MainRepository> implements CollectionContract.Presenter {

    public CollectionPresenter(@NonNull MainRepository repository, @NonNull CollectionContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
