package com.example.xiaomage.xingvoices.feature.login;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;

public class LogInContract {

    interface View extends BaseViewApi<Presenter> {
        void showUi();
        void gotoMain();
    }

    interface Presenter extends BasePresenterApi {
        void getLocalUser();
    }
}
