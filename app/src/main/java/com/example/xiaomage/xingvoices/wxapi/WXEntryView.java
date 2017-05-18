package com.example.xiaomage.xingvoices.wxapi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import butterknife.BindView;
import butterknife.OnClick;

public class WXEntryView extends BaseView<WXEntryContract.Presenter> implements WXEntryContract.View {

    @BindView(R.id.pb_wait)
    ProgressBar mPbWait;

    private IWXAPI mIWxApi;
    private AccessToken mAccessToken;
    private WxUserInfo mWxUserInfo;

    public void setIWxApi(IWXAPI IWxApi) {
        mIWxApi = IWxApi;
    }

    public WXEntryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.wxentry_view;
    }

    public void requestAccessToken(String code) {
        getPresenter().getAccessToken(code);
    }

    @Override
    public void setAccessToken(AccessToken accessToken) {
        mAccessToken = accessToken;
        getPresenter().getUserInfo(mAccessToken);
    }

    @Override
    public void setWxUserInfo(WxUserInfo userInfo) {
        mWxUserInfo = userInfo;
        Intent intent = MainActivity.getNewIntent(getContext(),mWxUserInfo);
        getContext().startActivity(intent);
    }
}
