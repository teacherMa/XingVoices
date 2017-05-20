package com.example.xiaomage.xingvoices.feature;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaomage on 2017/5/18.
 */

public class LogInActivity extends AppCompatActivity {

    @BindView(R.id.iv_wx_login)
    ImageView mIvWxLogin;

    private IWXAPI mIWxApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        ButterKnife.bind(this);

        mIWxApi = WXAPIFactory.createWXAPI(this, Constants.WxParamValue.APP_ID, true);

        mIWxApi.registerApp(Constants.WxParamValue.APP_ID);

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
        new AlertDialog.Builder(this)
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
    protected void onStop() {
        super.onStop();
        finish();
    }
}
