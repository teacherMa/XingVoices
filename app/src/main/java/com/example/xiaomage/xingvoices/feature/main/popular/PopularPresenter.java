package com.example.xiaomage.xingvoices.feature.main.popular;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

public class PopularPresenter extends BasePresenter<PopularContract.View, MainRepository> implements PopularContract.Presenter {

    public PopularPresenter(@NonNull MainRepository repository, @NonNull PopularContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestVoiceData(XingVoiceUserResp xingVoiceUserResp) {
        OnResultCallback<List<RemoteVoice>> callback = new OnResultCallback<List<RemoteVoice>>() {
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
        getRepository().requestVoicesList(callback, xingVoiceUserResp, Constants.VoiceType.POPULAR);
    }
}
