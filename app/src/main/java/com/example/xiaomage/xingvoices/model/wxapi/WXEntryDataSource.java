package com.example.xiaomage.xingvoices.model.wxapi;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

import okhttp3.ResponseBody;

public interface WXEntryDataSource {

    void getAccessToken(String code, OnResultCallback<AccessToken> callback);
    void getWxUserInfo(AccessToken accessToken, OnResultCallback<WxUserInfo>callback);
}
