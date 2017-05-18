package com.example.xiaomage.xingvoices.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> {

    private static final String USER_INFO = "user_info";

    @BindView(R.id.main_view)
    MainView mMainView;

    private WxUserInfo mWxUserInfo;

    public static Intent getNewIntent(Context context,WxUserInfo userInfo){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(USER_INFO,userInfo);
        return intent;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        mMainView.setWxUserInfo(mWxUserInfo);
        return new MainPresenter(
                Injection.provideMainRepository(),
                mMainView
        );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWxUserInfo = (WxUserInfo) getIntent().getSerializableExtra(USER_INFO);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_act;
    }

}
