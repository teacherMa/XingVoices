package com.example.xiaomage.xingvoices.api;

import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.bean.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.bean.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.model.bean.publishCommentResp.CommentResp;
import com.example.xiaomage.xingvoices.model.bean.likeCommentResp.LikeItResp;
import com.example.xiaomage.xingvoices.model.bean.publishVoiceResp.PublishResp;
import com.example.xiaomage.xingvoices.model.bean.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
                                  @Query(Constants.XingVoicesRequestParam.SEX) int sex,
                                  @Query(Constants.XingVoicesRequestParam.HEADPIC) String hedapic);


    @GET(Constants.XingVoicesApi.GET_USER)
    Call<BasicUserInfo> getUser(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                @Query(Constants.XingVoicesRequestParam.CID) String cid);

    @GET(Constants.XingVoicesApi.ALL_VOICE)
    Call<List<RemoteVoice>> getAllVoice(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                        @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                        @Query(Constants.XingVoicesRequestParam.IS_U) int is_u,
                                        @Query(Constants.XingVoicesRequestParam.CID) String cid,
                                        @Query(Constants.XingVoicesRequestParam.PAGE) int page,
                                        @Query(Constants.XingVoicesRequestParam.NUM) int num);

    @GET("{id}/file/{date}/{voiceName}")
    Call<ResponseBody> downloadVoice(@Path("id") String id,
                                     @Path("date") String date,
                                     @Path("voiceName") String voiceName);

    @GET(Constants.XingVoicesApi.ADD_VOICE)
    Call<PublishResp> addVoice(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                               @Query(Constants.XingVoicesRequestParam.UID) String uid,
                               @Query(Constants.XingVoicesRequestParam.TITLE) String title,
                               @Query(Constants.XingVoicesRequestParam.RE_URL) String reurl,
                               @Query(Constants.XingVoicesRequestParam.LENGTH) int length,
                               @Query(Constants.XingVoicesRequestParam.BACKGROUND) String bg,
                               @Query(Constants.XingVoicesRequestParam.ALL_BACKGROUND) String allBg);


    @GET(Constants.XingVoicesApi.MY_COLLECTION)
    Call<List<RemoteVoice>> getMyCollection(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                @Query(Constants.XingVoicesRequestParam.UID) String uid);

    @GET(Constants.XingVoicesApi.MY_FOLLOW)
    Call<List<RemoteVoice>> getMyFollow(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                  @Query(Constants.XingVoicesRequestParam.UID) String uid);

    @GET(Constants.XingVoicesApi.ADD_COMMENTS)
    Call<CommentResp> addComment(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                 @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                 @Query(Constants.XingVoicesRequestParam.VID) String vid,
                                 @Query(Constants.XingVoicesRequestParam.TYPE) int type,
                                 @Query(Constants.XingVoicesRequestParam.CONTENT) String content,
                                 @Query(Constants.XingVoicesRequestParam.CLENGTH) int cLength);


    @GET(Constants.XingVoicesApi.VOICE_TO_COMMENTS)
    Call<List<CommentBean>> getVoiceComment(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                      @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                      @Query(Constants.XingVoicesRequestParam.VID) String vid,
                                      @Query(Constants.XingVoicesRequestParam.TYPE) int type);


    @GET(Constants.XingVoicesApi.MY_VOICE_COMMENTS)
    Call<List<MyVoiceCommentResp>> getMyVoiceComments(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                                                      @Query(Constants.XingVoicesRequestParam.UID) String uid,
                                                      @Query(Constants.XingVoicesRequestParam.NUM) int num);


    @GET(Constants.XingVoicesApi.LIKE_IT)
    Call<LikeItResp> likeIt(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                            @Query(Constants.XingVoicesRequestParam.UID) String uid,
                            @Query(Constants.XingVoicesRequestParam.CID) String cid);

    @GET(Constants.XingVoicesApi.FOLLOW)
    Call<FollowResp> followIt(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                              @Query(Constants.XingVoicesRequestParam.UID) String uid,
                              @Query(Constants.XingVoicesRequestParam.CID) String cid,
                              @Query(Constants.XingVoicesRequestParam.STATE) int state);

    @Multipart
    @POST(Constants.XingVoicesApi.UPLOAD_OSS)
    Call<UploadResp> upload(@Query(Constants.XingVoicesRequestParam.CHANNEL) String channel,
                            @Query(Constants.XingVoicesRequestParam.UID) String uid,
                            @Part("description")RequestBody requestBody,
                            @Part MultipartBody.Part fileFirst,
                            @Part MultipartBody.Part fileSecond,
                            @Part MultipartBody.Part fileThird);


}
