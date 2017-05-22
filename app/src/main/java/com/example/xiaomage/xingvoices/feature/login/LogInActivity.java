package com.example.xiaomage.xingvoices.feature.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.Injection;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LogInActivity extends BaseActivity<LogInPresenter> {
    @BindView(R.id.login_view)
    LogInView mLoginView;

    private IWXAPI mIWxApi;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mIWxApi = WXAPIFactory.createWXAPI(this, Constants.WxParamValue.APP_ID, true);

        mIWxApi.registerApp(Constants.WxParamValue.APP_ID);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    protected LogInPresenter createPresenter() {
        return new LogInPresenter(
                Injection.provideMainRepository(),
                mLoginView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.login_act;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
