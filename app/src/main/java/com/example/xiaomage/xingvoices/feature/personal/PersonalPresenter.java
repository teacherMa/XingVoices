package com.example.xiaomage.xingvoices.feature.personal;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.Resp.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import java.util.List;

public class PersonalPresenter extends BasePresenter<PersonalContract.View, MainRepository> implements PersonalContract.Presenter {

    public PersonalPresenter(@NonNull MainRepository repository, @NonNull PersonalContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        XingVoiceUser xingVoiceUser = getView().getXingVoiceUser();
        if(null == xingVoiceUser){
            return;
        }
        requestUserInfo(xingVoiceUser);
    }

    @Override
    public void requestUserVoices(XingVoiceUser xingVoiceUser,int num) {
        OnResultCallback<List<RemoteVoice>> onResultCallback = new OnResultCallback<List<RemoteVoice>>() {
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
        getRepository().requestPopularVoicesList(onResultCallback,xingVoiceUser.getUid(),1,null,1,10*num);
    }

    @Override
    public void requestUserInfo(final XingVoiceUser xingVoiceUser) {
        OnResultCallback<BasicUserInfo> onResultCallback = new OnResultCallback<BasicUserInfo>() {
            @Override
            public void onSuccess(BasicUserInfo resultValue, int code) {
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
                requestUserVoices(xingVoiceUser,1);
            }
        };
        getRepository().getUserInfo(onResultCallback,null,xingVoiceUser.getUid());
    }

    @Override
    public void changeFollowState(String cid, int state) {
        OnResultCallback<FollowResp> onResultCallback = new OnResultCallback<FollowResp>() {
            @Override
            public void onSuccess(FollowResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().changeStateSuccess(resultValue.getInfo());
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);

            }
        };
        getRepository().changeFollowState(onResultCallback,cid,state);
    }
}
