package com.example.xiaomage.xingvoices.wxapi;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.wxapi.WXEntryRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import okhttp3.ResponseBody;

public class WXEntryPresenter extends BasePresenter<WXEntryContract.View, WXEntryRepository> implements WXEntryContract.Presenter {

    public WXEntryPresenter(@NonNull WXEntryRepository repository, @NonNull WXEntryContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void getAccessToken(String code) {

        OnResultCallback<AccessToken> mCallback = new OnResultCallback<AccessToken>() {
            @Override
            public void onSuccess(AccessToken resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().setAccessToken(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };

        getRepository().getAccessToken(code,mCallback);
    }

    @Override
    public void getUserInfo(AccessToken accessToken) {
        OnResultCallback<WxUserInfo> wxUserInfoOnResultCallback = new OnResultCallback<WxUserInfo>() {
            @Override
            public void onSuccess(WxUserInfo resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().setWxUserInfo(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().getWxUserInfo(accessToken,wxUserInfoOnResultCallback);
    }

    @Override
    public void downloadHeadPic(WxUserInfo info) {
        OnResultCallback<ResponseBody> resultCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().startNewActivity();
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().downloadHeadPic(info,resultCallback,null);
    }
}
