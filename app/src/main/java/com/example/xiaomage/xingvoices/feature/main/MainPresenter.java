package com.example.xiaomage.xingvoices.feature.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.Resp.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import okhttp3.ResponseBody;

public class MainPresenter extends BasePresenter<MainContract.View, MainRepository> implements MainContract.Presenter {

    public MainPresenter(@NonNull MainRepository repository, @NonNull MainContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        WxUserInfo info = getView().getWxUserInfo();
        if(null == info){
            getView().initMainUi();
            return;
        }
        login(info);

    }

    @Override
    public void login(final WxUserInfo info) {
        OnResultCallback<XingVoiceUserResp> callback = new OnResultCallback<XingVoiceUserResp>() {
            @Override
            public void onSuccess(XingVoiceUserResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().initMainUi();
                uploadHeadPic(info);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().login(callback,info,null);
    }

    @Override
    public void uploadHeadPic(WxUserInfo info) {

        final OnResultCallback<UploadResp> uploadCallback = new OnResultCallback<UploadResp>() {
            @Override
            public void onSuccess(UploadResp resultValue, int code) {
                if (getView() == null) {
                    return;
                }
                BaseUtil.showToast(resultValue.getData().getAFile());
            }

            @Override
            public void onFail(String errorMessage) {

            }
        };

        //先进行下载，将头像下载到本地
        OnResultCallback<ResponseBody> downCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                //然后进行上传，返回头像在服务器上的url
                getRepository().uploadHeadPic(uploadCallback);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null  == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().downloadHeadPic(downCallback,null,info.getHeadimgurl());


    }

}
