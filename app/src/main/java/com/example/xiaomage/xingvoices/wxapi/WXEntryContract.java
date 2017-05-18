package com.example.xiaomage.xingvoices.wxapi;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

public class WXEntryContract {

    interface View extends BaseViewApi<Presenter> {
        void setAccessToken(AccessToken accessToken);
        void setWxUserInfo(WxUserInfo userInfo);
    }

    interface Presenter extends BasePresenterApi {
        void getAccessToken(String code);
        void getUserInfo(AccessToken accessToken);
    }
}
