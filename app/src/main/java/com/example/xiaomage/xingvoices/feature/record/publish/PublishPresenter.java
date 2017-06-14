package com.example.xiaomage.xingvoices.feature.record.publish;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.Resp.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.model.record.RecordRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

public class PublishPresenter extends BasePresenter<PublishContract.View, RecordRepository> implements PublishContract.Presenter {

    public PublishPresenter(@NonNull RecordRepository repository, @NonNull PublishContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void audition(String path) {
       OnResultCallback<Integer> onResultCallback = new OnResultCallback<Integer>() {
           @Override
           public void onSuccess(Integer resultValue, int code) {
               if(null == getView()){
                   return;
               }
           }

           @Override
           public void onFail(String errorMessage) {
                BaseUtil.showToast(errorMessage);
           }
       };
        getRepository().audition(onResultCallback,path);
    }

    @Override
    public void upload(String voicePath, String originPicPath, String cropPicPath) {
        OnResultCallback<UploadResp> onResultCallback = new OnResultCallback<UploadResp>() {
            @Override
            public void onSuccess(UploadResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().uploadSuccess(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if (getView() == null) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().upload(onResultCallback,voicePath,originPicPath,cropPicPath);
    }

    @Override
    public void publishRecord(String title, String recordPath, int length, String cropPic, String originPic) {
        OnResultCallback<String> onResultCallback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                if (getView() == null) {
                    return;
                }
                getView().publishSuccess(true);
            }

            @Override
            public void onFail(String errorMessage) {
                if (getView() == null) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().publish(onResultCallback,title,recordPath,length,cropPic,originPic);
    }
}
