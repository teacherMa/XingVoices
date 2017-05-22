package com.example.xiaomage.xingvoices.feature.login;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class LogInPresenter extends BasePresenter<LogInContract.View, MainRepository> implements LogInContract.Presenter {

    public LogInPresenter(@NonNull MainRepository repository, @NonNull LogInContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        getLocalUser();
    }

    @Override
    public void getLocalUser() {
        OnResultCallback<XingVoiceUser> onResultCallback = new OnResultCallback<XingVoiceUser>() {
            @Override
            public void onSuccess(XingVoiceUser resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().gotoMain();
            }

            @Override
            public void onFail(String errorMessage) {
                getView().showUi();
            }
        };
        getRepository().getLocalUser(onResultCallback);
    }
}
