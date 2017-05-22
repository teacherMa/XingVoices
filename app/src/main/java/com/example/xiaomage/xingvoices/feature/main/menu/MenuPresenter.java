package com.example.xiaomage.xingvoices.feature.main.menu;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

public class MenuPresenter extends BasePresenter<MenuContract.View, MainRepository> implements MenuContract.Presenter {

    public MenuPresenter(@NonNull MainRepository repository, @NonNull MenuContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void getUserInfo() {

        final OnResultCallback<BasicUserInfo> callback = new OnResultCallback<BasicUserInfo>() {
            @Override
            public void onSuccess(BasicUserInfo resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().setBasicUserInfo(resultValue);
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
                getRepository().getUserInfo(callback,resultValue.getUid(),resultValue.getUid());
            }

            @Override
            public void onFail(String errorMessage) {
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().getLocalUser(userOnResultCallback);

    }
}
