package com.example.xiaomage.xingvoices.api;

import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.VoiceResp;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constants.WxApi.ACCESS_TOKEN)
    Call<AccessToken> getAccessToken(@Query(Constants.WxRequestParam.APP_ID) String appId,
                                     @Query(Constants.WxRequestParam.APP_SECRET) String secret,
                                     @Query(Constants.WxRequestParam.CODE) String code,
                                     @Query(Constants.WxRequestParam.GRANT_TYPE) String type);

    @GET(Constants.WxApi.USER_INFO)
    Call<WxUserInfo> getUserInfo(@Query(Constants.WxRequestParam.ACCESS_TOKEN) String accessToken,
                                 @Query(Constants.WxRequestParam.OPEN_ID) String openId);

    @GET("{className}/0")
    Call<ResponseBody> downloadHeadPic(@Path("className") String className);

    @GET(Constants.XingVoicesApi.LOGIN)
    Call<XingVoiceUserResp> login(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                  @Query(Constants.XingVoicesRequestParam.OPEN_ID) String openid,
                                  @Query(Constants.XingVoicesRequestParam.NICKNAME) String nickname,
                                  @Query(Constants.XingVoicesRequestParam.SEX) int sex);


    @GET(Constants.XingVoicesApi.GET_USER)
    Call<BasicUserInfo> getUser(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                @Query(Constants.XingVoicesRequestParam.CID) String cid);

    @GET(Constants.XingVoicesApi.ALL_VOICE)
    Call<List<RemoteVoice>> getAllVoice(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                        @Query(Constants.XingVoicesRequestParam.UID) String uid);

    @GET(Constants.XingVoicesApi.MY_COLLECTION)
    Call<List<RemoteVoice>> getMyCollection(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                @Query(Constants.XingVoicesRequestParam.UID) String uid);

    @GET(Constants.XingVoicesApi.MY_FOLLOW)
    Call<List<RemoteVoice>> getMyFollow(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                  @Query(Constants.XingVoicesRequestParam.UID) String uid);

    @GET(Constants.XingVoicesApi.VOICE_TO_COMMENTS)
    Call<List<CommentBean>> getVoiceComment(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                      @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                      @Query(Constants.XingVoicesRequestParam.VID) String vid,
                                      @Query(Constants.XingVoicesRequestParam.TYPE) int type);

}
