package com.example.xiaomage.xingvoices.model.main;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.Resp.shieldResp.ShieldResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.bean.Resp.collectionResp.CollectionResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.publishCommentResp.CommentResp;

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

    void requestCollectionVoicesList(OnResultCallback<List<RemoteVoice>>resultCallback,int num);

    void requestFollowVoicesList(OnResultCallback<List<RemoteVoice>>resultCallback,int num);

    void requestComment(OnResultCallback<List<CommentBean>> resultCallback, RemoteVoice voice,
                        XingVoiceUser bean,int commentType,int num);

    void downloadVoice(OnResultCallback<ResponseBody> resultCallback, ResponseBody responseBody,
                       String vUrl,String vId);

    void downloadHeadPic(OnResultCallback<ResponseBody> resultCallback,ResponseBody responseBody,
                         String url);

    void playVoice(OnResultCallback<Boolean> resultCallback,String vId);

    void likeIt(OnResultCallback<String> resultCallback,String cId);

    void playVoiceCom(OnResultCallback<Boolean> resultCallback,CommentBean commentBean);

    /**
     * 录音，返回本次录音的id，可以通过id查询路径*/
    void recordAudio(OnResultCallback<String> resultCallback, boolean toStart);

    void publishTextCom(OnResultCallback<CommentResp> resultCallback, String vId, String content);

    void publishVoiceCom(OnResultCallback<CommentResp> resultCallback, String vId, String cId, int cLength);

    void uploadHeadPic(OnResultCallback<UploadResp> resultCallback);

    /**
     * 关注/取消关注
     * @param state 期望的状态，0表示关注，1表示取消
     * @param cid 被（取消）关注者的id*/
    void changeFollowState(OnResultCallback<FollowResp> resultCallback,String cid,int state);

    void toCollection(OnResultCallback<CollectionResp> resultCallback,String cid,int state);

    /**
     * 请求我发布的语音的评论*/
    void requestMyVoiceComments(OnResultCallback<List<MyVoiceCommentResp>> resultCallback,int num);

    void shieldVoice(OnResultCallback<ShieldResp> resultCallback,String vid);

    void stopPlayVoice(OnResultCallback<String> resultCallback);

}
