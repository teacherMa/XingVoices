package com.example.xiaomage.xingvoices.feature.main.follow;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

import java.util.List;

public class FollowContract {

    interface View extends BaseViewApi<Presenter> {
        void loadData(List<RemoteVoice> data);
    }

    interface Presenter extends BasePresenterApi {
        void requestData();
    }
}
