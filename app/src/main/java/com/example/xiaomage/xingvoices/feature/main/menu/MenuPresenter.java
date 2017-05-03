package com.example.xiaomage.xingvoices.feature.main.menu;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class MenuPresenter extends BasePresenter<MenuContract.View, MainRepository> implements MenuContract.Presenter {

    public MenuPresenter(@NonNull MainRepository repository, @NonNull MenuContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
