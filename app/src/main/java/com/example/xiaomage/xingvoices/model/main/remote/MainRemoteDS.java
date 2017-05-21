package com.example.xiaomage.xingvoices.model.main.remote;

import com.example.xiaomage.xingvoices.api.ApiService;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.RetrofitClient;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.main.MainDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

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
                        info.getOpenid(), info.getNickname(), info.getSex())
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
                            final XingVoiceUserResp resp) {
        RetrofitClient.buildService(ApiService.class)
                .getUser(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid(),
                        resp.getUser().getUid())
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
    public void requestVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback,
                                  final XingVoiceUserResp resp, String dataType) {
        Call<List<RemoteVoice>> respCall = null;

        switch (dataType) {
            case Constants.VoiceType.POPULAR:
                respCall = RetrofitClient.buildService(ApiService.class)
                        .getAllVoice(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
            case Constants.VoiceType.FOLLOW:
                respCall = RetrofitClient.buildService(ApiService.class)
                        .getMyFollow(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
            case Constants.VoiceType.COLLECTION:
                RetrofitClient.buildService(ApiService.class)
                        .getMyCollection(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
            default:
                respCall = RetrofitClient.buildService(ApiService.class)
                        .getAllVoice(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
        }

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

    private static class SingletonHolder {
        private static final MainRemoteDS INSTANCE = new MainRemoteDS();
    }
}
