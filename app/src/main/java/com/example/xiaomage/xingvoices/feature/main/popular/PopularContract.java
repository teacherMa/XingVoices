package com.example.xiaomage.xingvoices.feature.main.popular;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;

import java.util.List;

public class PopularContract {

    interface View extends BaseViewApi<Presenter> {
        void loadData(List<RemoteVoice> data);
        void downloadSuccess(String vId);
        void playFinished();
    }

    interface Presenter extends BasePresenterApi {
        void requestAllPopularVoice();
        void downloadVoice(String vUrl,String vId);
        void playVoice(String vId);
    }
}
