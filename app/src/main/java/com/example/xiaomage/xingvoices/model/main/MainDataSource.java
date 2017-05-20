package com.example.xiaomage.xingvoices.model.main;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.VoiceResp;
import com.example.xiaomage.xingvoices.model.bean.User.UserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

import java.util.List;

public interface MainDataSource {

    /**
    *这里的login是使用微信的信息去登录星声服务器*/
    void login(OnResultCallback<UserResp> resultCallback, WxUserInfo info);

    void getUser(OnResultCallback<UserInfo> resultCallback, UserResp resp);

    void requestData(OnResultCallback<List<RemoteVoice>> resultCallback, UserResp resp, String dataType);

}
