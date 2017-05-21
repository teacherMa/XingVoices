package com.example.xiaomage.xingvoices.model.main;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

import java.util.List;

public interface MainDataSource {

    /**
    *这里的login是使用微信的信息去登录星声服务器,获取星声账号*/
    void login(OnResultCallback<XingVoiceUserResp> resultCallback, WxUserInfo info, XingVoiceUserResp xingVoiceUserResp);

    /**
     * 这里是通过星声账号，获取星声用户的基本信息，包括粉丝，关注数*/
    void getUserInfo(OnResultCallback<BasicUserInfo> resultCallback, XingVoiceUserResp resp);

    void getLocalUser(OnResultCallback<XingVoiceUser> callback);

    void requestVoicesList(OnResultCallback<List<RemoteVoice>> resultCallback, XingVoiceUserResp resp, String dataType);

    void requestComment(OnResultCallback<List<CommentBean>> resultCallback, RemoteVoice voice, XingVoiceUser bean,int commentType);

}
