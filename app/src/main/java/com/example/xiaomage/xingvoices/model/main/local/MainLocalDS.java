package com.example.xiaomage.xingvoices.model.main.local;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.VoiceResp;
import com.example.xiaomage.xingvoices.model.bean.User.UserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.main.MainDataSource;

import java.util.List;

public class MainLocalDS implements MainDataSource {
    private MainLocalDS() {
    }

    public static MainLocalDS getInstance() {
        return MainLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void login(OnResultCallback<UserResp> resultCallback, WxUserInfo info) {

    }

    @Override
    public void getUser(OnResultCallback<UserInfo> resultCallback, UserResp resp) {

    }

    @Override
    public void requestData(OnResultCallback<List<RemoteVoice>> resultCallback, UserResp resp, String dataType) {

    }

    private static class SingletonHolder {
        private static final MainLocalDS INSTANCE = new MainLocalDS();
    }
}
