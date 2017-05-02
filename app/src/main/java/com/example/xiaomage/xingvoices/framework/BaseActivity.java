package com.example.xiaomage.xingvoices.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This is BaseActivity .
 * It's responsibility is to make contact between View layer and Presenter layer .
 */
public abstract class BaseActivity<P extends BasePresenterApi> extends BaseCustomActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createPresenter();
    }


    /**
     * create a Presenter.
     *
     * @return Presenter of this Activity's View layer
     */
    @NonNull
    protected abstract P createPresenter();

}
