package com.example.xiaomage.xingvoices.feature.login;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class LogInActivity extends BaseActivity<LogInPresenter> {
    @BindView(R.id.login_view)
    LogInView mLoginView;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        ShareConfig shareConfig = ShareConfig.instance()
                .qqId(Constants.QQ_AAP_ID)
                .wxId(Constants.WxParamValue.APP_ID)
                .weiboId(Constants.SINA_APP_ID)
                .wxSecret(Constants.WxParamValue.APP_SECERT);
        ShareManager.init(shareConfig);

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
    protected void onPause() {
        super.onPause();
        finish();
    }

    @NeedsPermission(Manifest.permission_group.STORAGE)
    void login(){
        mLoginView.wxLogin();
    }

    @OnShowRationale(Manifest.permission_group.STORAGE)
    void needStorageInfo(final PermissionRequest request){
        new AlertDialog.Builder(this).setTitle(BaseUtil.getString(R.string.per_need_storage))
                .setMessage(BaseUtil.getString(R.string.per_why_need_storage))
                .setPositiveButton(BaseUtil.getString(R.string.alert_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(BaseUtil.getString(R.string.alert_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                        dialog.dismiss();
                    }
                }).show();
    }

    @OnNeverAskAgain(Manifest.permission_group.STORAGE)
    void requestAgain(){
        BaseUtil.showToast(BaseUtil.getString(R.string.per_do_yourself));
    }
}
