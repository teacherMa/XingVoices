package com.example.xiaomage.xingvoices.model.wxapi.local;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.wxapi.WXEntryDataSource;

public class WXEntryLocalDS implements WXEntryDataSource {
    private WXEntryLocalDS() {
    }

    public static WXEntryLocalDS getInstance() {
        return WXEntryLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void getAccessToken(String code, OnResultCallback<AccessToken> callback) {

    }

    @Override
    public void getWxUserInfo(AccessToken accessToken, OnResultCallback<WxUserInfo> callback) {

    }

    private static class SingletonHolder {
        private static final WXEntryLocalDS INSTANCE = new WXEntryLocalDS();
    }
}
