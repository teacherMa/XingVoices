package com.example.xiaomage.xingvoices.feature.main.menu;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.User.UserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;

public class MenuContract {

    interface View extends BaseViewApi<Presenter> {
        void setUserInfo(UserInfo userInfo);
    }

    interface Presenter extends BasePresenterApi {
        void getUserInfo(UserResp resp);
    }
}
