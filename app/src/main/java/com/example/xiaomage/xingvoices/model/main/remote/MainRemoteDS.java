package com.example.xiaomage.xingvoices.model.main.remote;

import com.example.xiaomage.xingvoices.api.ApiService;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.RetrofitClient;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.VoiceResp;
import com.example.xiaomage.xingvoices.model.bean.User.UserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;
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
    public void login(final OnResultCallback<UserResp> resultCallback, WxUserInfo info) {
        RetrofitClient.buildService(ApiService.class)
                .login(Constants.XingVoicesParamValue.CHANNEL,
                        info.getOpenid(), info.getNickname(), info.getSex())
                .enqueue(new Callback<UserResp>() {
                    @Override
                    public void onResponse(Call<UserResp> call, Response<UserResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<UserResp> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void getUser(final OnResultCallback<UserInfo> resultCallback, final UserResp resp) {
        RetrofitClient.buildService(ApiService.class)
                .getUser(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid(), resp.getUser().getUid())
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void requestData(final OnResultCallback<List<RemoteVoice>> resultCallback, final UserResp resp, String dataType) {
        Call<List<RemoteVoice>> respCall = null;

        switch (dataType) {
            case Constants.VoiceType.POPULAR:
                respCall = RetrofitClient.buildService(ApiService.class)
                        .getAllVoice(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
            /*case Constants.VoiceType.FOLLOW:
                respCall = RetrofitClient.buildService(ApiService.class)
                        .getMyFollow(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
            case Constants.VoiceType.COLLECTION:
                RetrofitClient.buildService(ApiService.class)
                        .getMyCollection(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;*/
            default:
                respCall = RetrofitClient.buildService(ApiService.class)
                        .getAllVoice(Constants.XingVoicesParamValue.CHANNEL, resp.getUser().getUid());
                break;
        }

        if(null == respCall){
            return;
        }
        respCall.enqueue(new Callback<List<RemoteVoice>>() {
            @Override
            public void onResponse(Call<List<RemoteVoice>> call, Response<List<RemoteVoice>> response) {
                if(null == response || !response.isSuccessful() || null == response.body()){
                    resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                    return;
                }
                resultCallback.onSuccess(response.body(),Constants.ResultCode.REMOTE);
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

    private static class SingletonHolder {
        private static final MainRemoteDS INSTANCE = new MainRemoteDS();
    }
}
