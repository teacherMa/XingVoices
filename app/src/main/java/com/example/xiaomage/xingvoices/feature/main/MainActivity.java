package com.example.xiaomage.xingvoices.feature.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.event.StopPlayVoice;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Injection;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity<MainPresenter> {

    private static final String USER_INFO = "user_info";

    @BindView(R.id.main_view)
    MainView mMainView;

    private WxUserInfo mWxUserInfo;

    public static Intent getNewIntent(Context context,WxUserInfo userInfo){
        Intent intent = new Intent(context,MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_INFO,userInfo);
        intent.putExtras(bundle);

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
        Intent intent = getIntent();
        mWxUserInfo = (WxUserInfo) intent.getSerializableExtra(USER_INFO);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_act;
    }

    @NeedsPermission(Manifest.permission_group.MICROPHONE)
    void tryRecord(){
        mMainView.toRecord();
    }

    @NeedsPermission(Manifest.permission_group.PHONE)
    void tryInit(){
        mMainView.toInitFragmentAndVp();
    }

    @OnShowRationale(Manifest.permission_group.MICROPHONE)
    void needStorageInfo(final PermissionRequest request){
        new AlertDialog.Builder(this).setTitle(BaseUtil.getString(R.string.per_need_mic))
                .setMessage(BaseUtil.getString(R.string.per_why_need_mic))
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

    @OnShowRationale(Manifest.permission_group.PHONE)
    void needPhoneInfo(final PermissionRequest request){
        new AlertDialog.Builder(this).setTitle(BaseUtil.getString(R.string.per_need_read_phone))
                .setMessage(BaseUtil.getString(R.string.per_why_need_read_phone))
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

    @OnNeverAskAgain(Manifest.permission_group.PHONE)
    void requestAgain(){
        BaseUtil.showToast(BaseUtil.getString(R.string.per_do_yourself));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mMainView.closeMenu()){
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            EventBus.getDefault().post(new StopPlayVoice(true));
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

   /* @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().post(new StopPlayVoice(true));
    }*/
}
