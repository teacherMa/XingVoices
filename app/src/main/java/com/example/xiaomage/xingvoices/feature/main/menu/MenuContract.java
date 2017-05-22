package com.example.xiaomage.xingvoices.feature.main.menu;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;

public class MenuContract {

    interface View extends BaseViewApi<Presenter> {
        void setBasicUserInfo(BasicUserInfo basicUserInfo);
    }

    interface Presenter extends BasePresenterApi {
        void getUserInfo();
    }
}
