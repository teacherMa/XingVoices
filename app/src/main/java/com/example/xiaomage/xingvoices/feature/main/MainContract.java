package com.example.xiaomage.xingvoices.feature.main;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

public class MainContract {

    interface View extends BaseViewApi<Presenter> {
        WxUserInfo getWxUserInfo();
        void initMainUi();
    }

    interface Presenter extends BasePresenterApi {
        void login(WxUserInfo info);
    }
}
