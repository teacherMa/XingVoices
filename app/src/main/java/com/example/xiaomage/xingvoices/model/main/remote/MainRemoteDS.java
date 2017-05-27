package com.example.xiaomage.xingvoices.model.main.remote;

import com.example.xiaomage.xingvoices.api.ApiService;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.RetrofitClient;
import com.example.xiaomage.xingvoices.model.UserManager;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.Resp.shieldResp.ShieldResp;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.bean.Resp.collectionResp.CollectionResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.publishCommentResp.CommentResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.likeCommentResp.LikeItResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.model.main.MainDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.FileUtil;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRemoteDS implements MainDataSource {
    private MainRemoteDS() {
    }

    public static MainRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void login(final OnResultCallback<XingVoiceUserResp> resultCallback,
                      WxUserInfo info, XingVoiceUserResp xingVoiceUserResp) {
        RetrofitClient.buildService(ApiService.class)
                .login(Constants.XingVoicesParamValue.CHANNEL,
                        info.getOpenid(), info.getNickname(), info.getSex(), info.getHeadimgurl())
                .enqueue(new Callback<XingVoiceUserResp>() {
                    @Override
                    public void onResponse(Call<XingVoiceUserResp> call,
                                           Response<XingVoiceUserResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<XingVoiceUserResp> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void getUserInfo(final OnResultCallback<BasicUserInfo> resultCallback,
                            String uid, String cid) {
        RetrofitClient.buildService(ApiService.class)
                .getUser(Constants.XingVoicesParamValue.CHANNEL, uid,
                        cid)
                .enqueue(new Callback<BasicUserInfo>() {
                    @Override
                    public void onResponse(Call<BasicUserInfo> call, Response<BasicUserInfo> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<BasicUserInfo> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void getLocalUser(OnResultCallback<XingVoiceUser> callback) {

    }

    @Override
    public void requestPopularVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback,
                                         String uid, int is_u, String cid, int page, int num) {
        Call<List<RemoteVoice>> respCall = RetrofitClient.buildService(ApiService.class)
                .getAllVoice(Constants.XingVoicesParamValue.CHANNEL, uid, is_u, cid, page, num);
        if (null == respCall) {
            return;
        }
        respCall.enqueue(new Callback<List<RemoteVoice>>() {
            @Override
            public void onResponse(Call<List<RemoteVoice>> call, Response<List<RemoteVoice>> response) {
                if (null == response || !response.isSuccessful() || null == response.body()) {
                    resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                    return;
                }
                resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
            }

            @Override
            public void onFailure(Call<List<RemoteVoice>> call, Throwable t) {
                if (null == t || null == t.getMessage()) {
                    resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                    return;
                }
                resultCallback.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void requestCollectionVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback, int num) {
        RetrofitClient.buildService(ApiService.class)
                .getMyCollection(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),num)
                .enqueue(new Callback<List<RemoteVoice>>() {
                    @Override
                    public void onResponse(Call<List<RemoteVoice>> call, Response<List<RemoteVoice>> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<List<RemoteVoice>> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void requestFollowVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback, int num) {
        RetrofitClient.buildService(ApiService.class)
                .getMyFollow(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),num)
                .enqueue(new Callback<List<RemoteVoice>>() {
                    @Override
                    public void onResponse(Call<List<RemoteVoice>> call, Response<List<RemoteVoice>> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<List<RemoteVoice>> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void requestComment(final OnResultCallback<List<CommentBean>> resultCallback,
                               RemoteVoice voice, XingVoiceUser bean, int commentType) {
        RetrofitClient.buildService(ApiService.class)
                .getVoiceComment(Constants.XingVoicesParamValue.CHANNEL, bean.getUid(),
                        voice.getVid(), commentType)
                .enqueue(new Callback<List<CommentBean>>() {
                    @Override
                    public void onResponse(Call<List<CommentBean>> call,
                                           Response<List<CommentBean>> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<List<CommentBean>> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void downloadVoice(final OnResultCallback<ResponseBody> resultCallback,
                              ResponseBody responseBody, String vUrl, String vId) {

        String[] strings = vUrl.split("/");

        String id = strings[strings.length - 4];
        String date = strings[strings.length - 2];
        String voiceName = strings[strings.length - 1];

        RetrofitClient.buildDownloadVoiceService(ApiService.class)
                .downloadVoice(id, date, voiceName)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void downloadHeadPic(final OnResultCallback<ResponseBody> resultCallback, ResponseBody responseBody, String url) {
        String[] strings = url.split("/");
        String id = strings[strings.length-2];

        RetrofitClient.buildDownloadWxHeadPicService(ApiService.class)
                .downloadHeadPic(id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void playVoice(OnResultCallback<Boolean> resultCallback, String vId) {

    }

    @Override
    public void likeIt(final OnResultCallback<String> resultCallback, String cId) {
        RetrofitClient.buildService(ApiService.class)
                .likeIt(Constants.XingVoicesParamValue.CHANNEL, UserManager.getInstance()
                        .getCurrentUser().getId(),cId )
                .enqueue(new Callback<LikeItResp>() {
                    @Override
                    public void onResponse(Call<LikeItResp> call, Response<LikeItResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body().getInfo(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<LikeItResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void playVoiceCom(OnResultCallback<Boolean> resultCallback, CommentBean commentBean) {

    }

    @Override
    public void recordAudio(OnResultCallback<String> resultCallback, boolean toStart) {

    }

    @Override
    public void publishTextCom(final OnResultCallback<CommentResp> resultCallback, String vId, String content) {
        RetrofitClient.buildService(ApiService.class)
                .addComment(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                                getCurrentUser().getId(), vId,0,content,0)
                .enqueue(new Callback<CommentResp>() {
                    @Override
                    public void onResponse(Call<CommentResp> call, Response<CommentResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<CommentResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void publishVoiceCom(final OnResultCallback<CommentResp> resultCallback, final String vId, String cId, final int cLength) {
        String comVoicePath = FileUtil.getVoicePath(cId);
        if(null == comVoicePath){
            resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
            return;
        }
        File fileFirst = new File(comVoicePath);
        if(!fileFirst.exists()){
            resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
            return;
        }

        RequestBody requestBodyFirst = RequestBody.create(MediaType.parse("multipart/form-data"), fileFirst);
        MultipartBody.Part bodyFirst = MultipartBody.Part.createFormData("aFile",fileFirst.getName(),requestBodyFirst);
        String descriptionString = "This is a description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        //先做数据的上传，得到评论在服务器上的url
        RetrofitClient.buildService(ApiService.class)
                .upload(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),description,null,null,bodyFirst)
                .enqueue(new Callback<UploadResp>() {
                    @Override
                    public void onResponse(Call<UploadResp> call, Response<UploadResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        //上传成功了，再发布评论
                        RetrofitClient.buildService(ApiService.class)
                                .addComment(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                                        getCurrentUser().getId(),vId,1,response.body().getData().getAFile(),cLength)
                                .enqueue(new Callback<CommentResp>() {
                                    @Override
                                    public void onResponse(Call<CommentResp> call, Response<CommentResp> response) {
                                        if (null == response || !response.isSuccessful() || null == response.body()) {
                                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                                            return;
                                        }
                                        resultCallback.onSuccess(response.body(),Constants.ResultCode.REMOTE);
                                    }

                                    @Override
                                    public void onFailure(Call<CommentResp> call, Throwable t) {
                                        if (t == null || null == t.getMessage()) {
                                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                                            return;
                                        }
                                        resultCallback.onFail(t.getMessage());
                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<UploadResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void uploadHeadPic(final OnResultCallback<UploadResp> resultCallback) {
        String path = FileUtil.AVATAR_FILE.concat("/").concat("HEAD").concat(".png");

        File fileFirst = new File(path);
        if(!fileFirst.exists()){
            resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
            return;
        }

        RequestBody requestBodyFirst = RequestBody.create(MediaType.parse("multipart/form-data"), fileFirst);
        MultipartBody.Part bodyFirst = MultipartBody.Part.createFormData("aFile",fileFirst.getName(),requestBodyFirst);
        String descriptionString = "This is a description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        RetrofitClient.buildService(ApiService.class)
                .editUser(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),description,bodyFirst)
                .enqueue(new Callback<UploadResp>() {
                    @Override
                    public void onResponse(Call<UploadResp> call, Response<UploadResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(),Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<UploadResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });

    }

    @Override
    public void changeFollowState(final OnResultCallback<FollowResp> resultCallback, String cid, int state) {
        RetrofitClient.buildService(ApiService.class)
                .followIt(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),cid,state)
                .enqueue(new Callback<FollowResp>() {
                    @Override
                    public void onResponse(Call<FollowResp> call, Response<FollowResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<FollowResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void toCollection(final OnResultCallback<CollectionResp> resultCallback, String cid, int state) {
        RetrofitClient.buildService(ApiService.class)
                .collection(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),cid,state)
                .enqueue(new Callback<CollectionResp>() {
                    @Override
                    public void onResponse(Call<CollectionResp> call, Response<CollectionResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<CollectionResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void requestMyVoiceComments(final OnResultCallback<List<MyVoiceCommentResp>> resultCallback, int num) {
        RetrofitClient.buildService(ApiService.class)
                .getMyVoiceComments(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),num*10)
                .enqueue(new Callback<List<MyVoiceCommentResp>>() {
                    @Override
                    public void onResponse(Call<List<MyVoiceCommentResp>> call, Response<List<MyVoiceCommentResp>> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<List<MyVoiceCommentResp>> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void shieldVoice(final OnResultCallback<ShieldResp> resultCallback, String vid) {
        RetrofitClient.buildService(ApiService.class)
                .shieldVoice(Constants.XingVoicesParamValue.CHANNEL,UserManager.getInstance().
                        getCurrentUser().getId(),vid)
                .enqueue(new Callback<ShieldResp>() {
                    @Override
                    public void onResponse(Call<ShieldResp> call, Response<ShieldResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<ShieldResp> call, Throwable t) {
                        if (t == null || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void stopPlayVoice(OnResultCallback<String> resultCallback) {

    }

    private static class SingletonHolder {
        private static final MainRemoteDS INSTANCE = new MainRemoteDS();
    }
}
