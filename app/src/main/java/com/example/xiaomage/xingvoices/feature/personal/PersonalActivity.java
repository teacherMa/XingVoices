package com.example.xiaomage.xingvoices.feature.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalActivity extends BaseActivity<PersonalPresenter> {
    @BindView(R.id.personal_view)
    PersonalView mPersonalView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected PersonalPresenter createPresenter() {
        return new PersonalPresenter(
                Injection.providePersonalRepository(),
                mPersonalView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.personal_act;
    }
}
