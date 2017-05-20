package com.example.xiaomage.xingvoices.feature.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.model.bean.User.UserBean;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity<PersonalPresenter> {

    private static final String ARG_USER_BEAN = "user bean";

    @BindView(R.id.personal_view)
    PersonalView mPersonalView;

    private UserBean mUserBean;

    public static Intent getNewIntent(UserBean userBean, Context context){
        Intent intent = new Intent(context,PersonalActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_USER_BEAN,userBean);

        intent.putExtras(bundle);

        return intent;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected PersonalPresenter createPresenter() {

        mUserBean = (UserBean)getIntent().getSerializableExtra(ARG_USER_BEAN);
        mPersonalView.setUserBean(mUserBean);

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
