package com.example.xiaomage.xingvoices.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiaomage.xingvoices.utils.ActivityController;

import butterknife.ButterKnife;

public abstract class BaseCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    protected abstract void initView(@Nullable Bundle savedInstanceState);


    /**
     * set resource id
     *
     * @return layout id of current activity
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    protected void onDestroy() {
        ActivityController.removeActivity(this);
        super.onDestroy();
    }
}
