package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.framework.BaseActivity;

public class TextComentActivity extends BaseActivity<TextComentPresenter> {
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        
    }

    @NonNull
    @Override
    protected TextComentPresenter createPresenter() {
        return new TextComentPresenter(
            Injection.provideTargetTrpository,
            mViewTextComent
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.TextComent_act;
    }
}
