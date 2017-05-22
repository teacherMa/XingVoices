package com.example.xiaomage.xingvoices.feature.main.popular;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import java.util.List;

public class PopularPresenter extends BasePresenter<PopularContract.View, MainRepository> implements PopularContract.Presenter {

    public PopularPresenter(@NonNull MainRepository repository, @NonNull PopularContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestAllPopularVoice() {
        final OnResultCallback<List<RemoteVoice>> callback = new OnResultCallback<List<RemoteVoice>>() {
            @Override
            public void onSuccess(List<RemoteVoice> resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().loadData(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        OnResultCallback<XingVoiceUser> userOnResultCallback = new OnResultCallback<XingVoiceUser>() {
            @Override
            public void onSuccess(XingVoiceUser resultValue, int code) {
                getRepository().requestPopularVoicesList(callback, resultValue.getUid(),
                        0,null,1,10);
            }

            @Override
            public void onFail(String errorMessage) {
                callback.onFail(errorMessage);
            }
        };
        getRepository().getLocalUser(userOnResultCallback);
    }
}
