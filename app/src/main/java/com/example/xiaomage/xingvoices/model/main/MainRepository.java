package com.example.xiaomage.xingvoices.model.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BaseRepository;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainRepository extends BaseRepository implements MainDataSource {
    private static Lock sLock = new ReentrantLock();
    private static MainRepository INSTANCE = null;
    private final MainDataSource mLocalDS;
    private final MainDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private MainRepository(@NonNull MainDataSource localDS, @NonNull MainDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static MainRepository getInstance(@NonNull MainDataSource localDS, @NonNull MainDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new MainRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }

    @Override
    public void login(final OnResultCallback<XingVoiceUserResp> resultCallback, final WxUserInfo info, XingVoiceUserResp xingVoiceUserResp) {
        OnResultCallback<XingVoiceUserResp> callback = new OnResultCallback<XingVoiceUserResp>() {
            @Override
            public void onSuccess(XingVoiceUserResp resultValue, int code) {
                mLocalDS.login(resultCallback,info,resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.login(callback,info,null);
    }

    @Override
    public void getUserInfo(final OnResultCallback<BasicUserInfo> resultCallback, XingVoiceUserResp resp) {
        OnResultCallback<BasicUserInfo> callback = new OnResultCallback<BasicUserInfo>() {
            @Override
            public void onSuccess(BasicUserInfo resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.getUserInfo(callback,resp);
    }

    @Override
    public void getLocalUser(OnResultCallback<XingVoiceUser> callback) {

    }

    @Override
    public void requestVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback, final XingVoiceUserResp resp, String dataType) {
        OnResultCallback<List<RemoteVoice>> callback = new OnResultCallback<List<RemoteVoice>>() {
            @Override
            public void onSuccess(List<RemoteVoice> resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.requestVoicesList(callback,resp, dataType);
    }

    @Override
    public void requestComment(final OnResultCallback<List<CommentBean>> resultCallback, final RemoteVoice voice, XingVoiceUser bean, final int commentType) {
        OnResultCallback<XingVoiceUser> loackCallback = new OnResultCallback<XingVoiceUser>() {
            @Override
            public void onSuccess(XingVoiceUser resultValue, int code) {
                mRemoteDS.requestComment(resultCallback,voice,resultValue,commentType);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.getLocalUser(loackCallback);
    }
}
