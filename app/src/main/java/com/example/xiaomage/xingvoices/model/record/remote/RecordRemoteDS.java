package com.example.xiaomage.xingvoices.model.record.remote;

import com.example.xiaomage.xingvoices.api.ApiService;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.RetrofitClient;
import com.example.xiaomage.xingvoices.model.UserManager;
import com.example.xiaomage.xingvoices.model.bean.publish.PublishResp;
import com.example.xiaomage.xingvoices.model.bean.upload.UploadResp;
import com.example.xiaomage.xingvoices.model.record.RecordDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordRemoteDS implements RecordDataSource {
    private RecordRemoteDS() {
    }

    public static RecordRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void recordAudio(OnResultCallback<String> resultCallback, boolean toStart) {

    }

    @Override
    public void deleteAudio(OnResultCallback<Boolean> resultCallback, String file) {

    }

    @Override
    public void audition(OnResultCallback<Integer> resultCallback, String path) {

    }

    @Override
    public void upload(final OnResultCallback<UploadResp> resultCallback, String voicePath,
                       String originPicPath, String cropPicPath) {

        File fileFirst = new File(voicePath);
        File fileSecond = new File(originPicPath);
        File fileThird = new File(cropPicPath);
        if(!fileFirst.exists() || !fileSecond.exists() || !fileThird.exists()){
            resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
        }

        RequestBody requestBodyFirst = RequestBody.create(MediaType.parse("multipart/form-data"), fileFirst);
        RequestBody requestBodySecond = RequestBody.create(MediaType.parse("multipart/form-data"), fileSecond);
        RequestBody requestBodyThird = RequestBody.create(MediaType.parse("multipart/form-data"), fileThird);

        MultipartBody.Part bodyFirst = MultipartBody.Part.createFormData("aFile",fileFirst.getName(),requestBodyFirst);
        MultipartBody.Part bodySecond = MultipartBody.Part.createFormData("bFile",fileSecond.getName(),requestBodySecond);
        MultipartBody.Part bodyThird = MultipartBody.Part.createFormData("cFile",fileThird.getName(),requestBodyThird);

        String descriptionString = "This is a description";

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        RetrofitClient.buildService(ApiService.class)
                .upload(Constants.XingVoicesParamValue.CHANNEL,
                        UserManager.getInstance().getCurrentUser().getId(),
                        description,bodyFirst,bodySecond,bodyThird)
                .enqueue(new Callback<UploadResp>() {
                    @Override
                    public void onResponse(Call<UploadResp> call, Response<UploadResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<UploadResp> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    @Override
    public void publish(final OnResultCallback<String> resultCallback, String title, String recordPath, int length, String cropPic, String originPic) {
        RetrofitClient.buildService(ApiService.class)
                .addVoice(Constants.XingVoicesParamValue.CHANNEL,
                        UserManager.getInstance().getCurrentUser().getId(),
                        title,recordPath,length,cropPic,originPic)
                .enqueue(new Callback<PublishResp>() {
                    @Override
                    public void onResponse(Call<PublishResp> call, Response<PublishResp> response) {
                        if (null == response || !response.isSuccessful() || null == response.body()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onSuccess(response.body().getInfo(), Constants.ResultCode.REMOTE);
                    }

                    @Override
                    public void onFailure(Call<PublishResp> call, Throwable t) {
                        if (null == t || null == t.getMessage()) {
                            resultCallback.onFail(Constants.ResponseError.SERVER_ERROR);
                            return;
                        }
                        resultCallback.onFail(t.getMessage());
                    }
                });
    }

    private static class SingletonHolder {
        private static final RecordRemoteDS INSTANCE = new RecordRemoteDS();
    }
}
