package com.example.xiaomage.xingvoices.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * All fragment's father .
 * Something is the same with {@link BaseActivity}
 */
public abstract class BaseFragment<P extends BasePresenterApi> extends BaseCustomFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createPresenter();
    }

    protected abstract P createPresenter();


}
