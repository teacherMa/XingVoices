package com.example.xiaomage.xingvoices.feature.main.menu;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.User.UserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;
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
    public void getUserInfo(UserResp resp) {
        OnResultCallback<UserInfo> callback = new OnResultCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().setUserInfo(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().getUser(callback,resp);
    }
}
