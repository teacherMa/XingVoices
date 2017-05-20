package com.example.xiaomage.xingvoices.feature.personal;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.UserBean;

import java.util.List;

public class PersonalContract {

    interface View extends BaseViewApi<Presenter> {
        void loadData(List<RemoteVoice> voices);
        UserBean getUserBean();
    }

    interface Presenter extends BasePresenterApi {
        void requestUserVoices(UserBean userBean);
    }
}
