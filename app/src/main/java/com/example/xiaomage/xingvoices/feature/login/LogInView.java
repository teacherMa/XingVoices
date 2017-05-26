package com.example.xiaomage.xingvoices.feature.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import me.shaohui.shareutil.LoginUtil;
import me.shaohui.shareutil.login.LoginListener;
import me.shaohui.shareutil.login.LoginPlatform;
import me.shaohui.shareutil.login.LoginResult;

public class LogInView extends BaseView<LogInContract.Presenter> implements LogInContract.View {

    @BindView(R.id.iv_wx_login)
    ImageView mIvWxLogin;
    @BindView(R.id.rl_login_view)
    RelativeLayout mRlLoginView;


    public LogInView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.login_view;
    }

    @OnClick(R.id.iv_wx_login)
    public void onViewClicked() {
        int phoneSDK = Build.VERSION.SDK_INT;
        if(phoneSDK>=23) {
            LogInActivityPermissionsDispatcher.loginWithCheck((LogInActivity) getContext());
        }else {
            wxLogin();
        }
    }

    @Override
    public void showUi() {
        mRlLoginView.setVisibility(VISIBLE);
    }

    @Override
    public void gotoMain() {
        Intent intent = MainActivity.getNewIntent(getContext(),null);
        getContext().startActivity(intent);
    }

    public void wxLogin(){
        final LoginListener loginListener = new LoginListener() {
            @Override
            public void loginSuccess(LoginResult result) {
                WxUserInfo userInfo = new WxUserInfo();
                userInfo.setNickname(result.getUserInfo().getNickname());
                userInfo.setHeadimgurl(result.getUserInfo().getHeadImageUrl());
                userInfo.setOpenid(result.getUserInfo().getOpenId());
                userInfo.setSex(result.getUserInfo().getSex());

                Intent intent = MainActivity.getNewIntent(getContext(),userInfo);
                getContext().startActivity(intent);

                mIvWxLogin.setVisibility(GONE);
                ((LogInActivity)getContext()).finish();
            }

            @Override
            public void loginFailure(Exception e) {

            }

            @Override
            public void loginCancel() {

            }
        };
        LoginUtil.login(getContext(), LoginPlatform.WX,loginListener,true);
    }
}
