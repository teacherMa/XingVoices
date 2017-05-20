package com.example.xiaomage.xingvoices.model.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BaseRepository;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.VoiceResp;
import com.example.xiaomage.xingvoices.model.bean.User.UserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.UserResp;
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
    public void login(final OnResultCallback<UserResp> resultCallback, WxUserInfo info) {
        OnResultCallback<UserResp> callback = new OnResultCallback<UserResp>() {
            @Override
            public void onSuccess(UserResp resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.login(callback,info);
    }

    @Override
    public void getUser(final OnResultCallback<UserInfo> resultCallback, UserResp resp) {
        OnResultCallback<UserInfo> callback = new OnResultCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.getUser(callback,resp);
    }

    @Override
    public void requestData(final OnResultCallback<List<RemoteVoice>> resultCallback, final UserResp resp, String dataType) {
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
        mRemoteDS.requestData(callback,resp, dataType);
    }
}
