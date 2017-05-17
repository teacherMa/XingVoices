package com.example.xiaomage.xingvoices.wxapi;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.wxapi.WXEntryRepository;

public class WXEntryPresenter extends BasePresenter<WXEntryContract.View, WXEntryRepository> implements WXEntryContract.Presenter {

    public WXEntryPresenter(@NonNull WXEntryRepository repository, @NonNull WXEntryContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
