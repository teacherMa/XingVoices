package com.example.xiaomage.xingvoices.feature.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;

public class LogInView extends BaseView<LogInContract.Presenter> implements LogInContract.View {

    @BindView(R.id.iv_wx_login)
    ImageView mIvWxLogin;
    @BindView(R.id.rl_login_view)
    RelativeLayout mRlLoginView;

    private IWXAPI mIWxApi;

    public LogInView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mIWxApi = WXAPIFactory.createWXAPI(getContext(), Constants.WxParamValue.APP_ID, true);

        mIWxApi.registerApp(Constants.WxParamValue.APP_ID);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.login_view;
    }

    @OnClick(R.id.iv_wx_login)
    public void onViewClicked() {
        if (mIWxApi.isWXAppInstalled()) {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            mIWxApi.sendReq(req);
            return;
        }
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.wx_not_installed)
                .setMessage(R.string.wx_not_installed_message)
                .setPositiveButton(R.string.wx_to_installed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
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
}
