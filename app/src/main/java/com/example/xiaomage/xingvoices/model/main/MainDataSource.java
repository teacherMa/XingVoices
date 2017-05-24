package com.example.xiaomage.xingvoices.model.main;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

import java.util.List;

import okhttp3.ResponseBody;

public interface MainDataSource {

    /**
    *这里的login是使用微信的信息去登录星声服务器,获取星声账号*/
    void login(OnResultCallback<XingVoiceUserResp> resultCallback, WxUserInfo info,
               XingVoiceUserResp xingVoiceUserResp);

    /**
     * 获取星声用户的基本信息，包括粉丝，关注数
     * @param uid 当前浏览用户的id
     * @param cid 被获取的用户id*/
    void getUserInfo(OnResultCallback<BasicUserInfo> resultCallback, String uid,String cid);

    void getLocalUser(OnResultCallback<XingVoiceUser> callback);

    void requestPopularVoicesList(OnResultCallback<List<RemoteVoice>> resultCallback, String uid ,
                                  int is_u, String cid, int page, int num);

    void requestComment(OnResultCallback<List<CommentBean>> resultCallback, RemoteVoice voice,
                        XingVoiceUser bean,int commentType);

    void downloadVoice(OnResultCallback<ResponseBody> resultCallback, ResponseBody responseBody,
                       String vUrl,String vId);

    void playVoice(OnResultCallback<Boolean> resultCallback,String vId);

}
