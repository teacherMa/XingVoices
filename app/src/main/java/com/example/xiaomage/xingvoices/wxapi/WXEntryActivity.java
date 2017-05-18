package com.example.xiaomage.xingvoices.wxapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.LogInActivity;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.utils.ActivityController;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.Injection;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;

public class WXEntryActivity extends BaseActivity<WXEntryPresenter> implements IWXAPIEventHandler {
    @BindView(R.id.wx_view)
    WXEntryView mWxView;

    private IWXAPI mIWxApi;
    private String mCode;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    @NonNull
    @Override
    protected WXEntryPresenter createPresenter() {
        mWxView.setIWxApi(mIWxApi);
        return new WXEntryPresenter(
                Injection.provideWXEntryRepository(),
                mWxView
        );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mIWxApi = WXAPIFactory.createWXAPI(this, Constants.WxLogin.APP_ID,true);

        mIWxApi.registerApp(Constants.WxLogin.APP_ID);

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mIWxApi.handleIntent(getIntent(),this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.wxentry_act;
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        if(null == baseResp){
            BaseUtil.showToast(Constants.WxLogin.NULL_RESP);
        }

        if(baseResp instanceof SendAuth.Resp){

            SendAuth.Resp resp = (SendAuth.Resp)baseResp;
            if(resp.errCode != 0){
                return;
            }

            mCode = resp.code;
            mWxView.requestAccessToken(mCode);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
