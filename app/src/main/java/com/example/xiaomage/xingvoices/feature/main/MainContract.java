package com.example.xiaomage.xingvoices.feature.main;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

public class MainContract {

    interface View extends BaseViewApi<Presenter> {
        WxUserInfo getWxUserInfo();
        void initUserResp(UserResp resp);
    }

    interface Presenter extends BasePresenterApi {
        void login(WxUserInfo info);
    }
}
