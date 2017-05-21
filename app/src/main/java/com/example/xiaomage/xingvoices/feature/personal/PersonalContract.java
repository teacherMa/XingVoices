package com.example.xiaomage.xingvoices.feature.personal;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;

import java.util.List;

public class PersonalContract {

    interface View extends BaseViewApi<Presenter> {
        void loadData(List<RemoteVoice> voices);
        XingVoiceUser getXingVoiceUser();
    }

    interface Presenter extends BasePresenterApi {
        void requestUserVoices(XingVoiceUser xingVoiceUser);
    }
}
