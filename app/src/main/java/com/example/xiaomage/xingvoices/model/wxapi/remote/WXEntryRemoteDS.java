package com.example.xiaomage.xingvoices.model.wxapi.remote;

import com.example.xiaomage.xingvoices.api.ApiService;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.RetrofitClient;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.wxapi.WXEntryDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WXEntryRemoteDS implements WXEntryDataSource {
    private WXEntryRemoteDS() {
    }

    public static WXEntryRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void getAccessToken(String code, final OnResultCallback<AccessToken> callback) {
        RetrofitClient.buildWeiXinService(ApiService.class)
                .getAccessToken(Constants.WxParamValue.APP_ID, Constants.WxParamValue.APP_SECERT,
                        code, Constants.WxParamValue.GRANT_TYPE)
                .enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            callback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        callback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            callback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        callback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void getWxUserInfo(AccessToken accessToken, final OnResultCallback<WxUserInfo> callback) {
        RetrofitClient.buildWeiXinService(ApiService.class)
                .getUserInfo(accessToken.getAccess_token(), accessToken.getOpenid())
                .enqueue(new Callback<WxUserInfo>() {
                    @Override
                    public void onResponse(Call<WxUserInfo> call, Response<WxUserInfo> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            callback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        callback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<WxUserInfo> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            callback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        callback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void downloadHeadPic(WxUserInfo info, final OnResultCallback<ResponseBody> callback, final ResponseBody body) {

        String[] apis = info.getHeadimgurl().split("/");
        String api = apis[apis.length-2];

        RetrofitClient.buildDownloadHeadPicService(ApiService.class)
                .downloadHeadPic(api)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(null == response || !response.isSuccessful() || null == response.body()){
                            callback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        callback.onSuccess(response.body(),Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(null == t || null == t.getMessage()){
                            callback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        callback.onFail(t.getMessage());
                    }
                });
    }

    private static class SingletonHolder {
        private static final WXEntryRemoteDS INSTANCE = new WXEntryRemoteDS();
    }
}
