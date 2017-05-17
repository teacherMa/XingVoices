package com.example.xiaomage.xingvoices.wxapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import butterknife.BindView;
import butterknife.OnClick;

public class WXEntryView extends BaseView<WXEntryContract.Presenter> implements WXEntryContract.View {

    @BindView(R.id.iv_wx_login)
    ImageView mIvWxLogin;

    private IWXAPI mIWxApi;

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

    @OnClick(R.id.iv_wx_login)
    public void onViewClicked() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        mIWxApi.sendReq(req);
    }
}
