package com.example.xiaomage.xingvoices.feature.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

public class MainPresenter extends BasePresenter<MainContract.View, MainRepository> implements MainContract.Presenter {

    public MainPresenter(@NonNull MainRepository repository, @NonNull MainContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        WxUserInfo info = getView().getWxUserInfo();
        if(null == info){
            return;
        }
        login(info);
    }

    @Override
    public void login(WxUserInfo info) {
        OnResultCallback<XingVoiceUserResp> callback = new OnResultCallback<XingVoiceUserResp>() {
            @Override
            public void onSuccess(XingVoiceUserResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().initUserResp(resultValue);
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

}
