package com.example.xiaomage.xingvoices.model.wxapi;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BaseRepository;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WXEntryRepository extends BaseRepository implements WXEntryDataSource {
    private static Lock sLock = new ReentrantLock();
    private static WXEntryRepository INSTANCE = null;
    private final WXEntryDataSource mLocalDS;
    private final WXEntryDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private WXEntryRepository(@NonNull WXEntryDataSource localDS, @NonNull WXEntryDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static WXEntryRepository getInstance(@NonNull WXEntryDataSource localDS, @NonNull WXEntryDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new WXEntryRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }

    @Override
    public void getAccessToken(String code, final OnResultCallback<AccessToken> callback) {
        OnResultCallback<AccessToken> accessTokenOnResultCallback = new OnResultCallback<AccessToken>() {
            @Override
            public void onSuccess(AccessToken resultValue, int code) {
                callback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                callback.onFail(errorMessage);
            }
        };
        mRemoteDS.getAccessToken(code,accessTokenOnResultCallback);
    }

    @Override
    public void getWxUserInfo(AccessToken accessToken, final OnResultCallback<WxUserInfo> callback) {
        OnResultCallback<WxUserInfo> wxUserInfoOnResultCallback = new OnResultCallback<WxUserInfo>() {
            @Override
            public void onSuccess(WxUserInfo resultValue, int code) {
                callback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                callback.onFail(errorMessage);
            }
        };
        mRemoteDS.getWxUserInfo(accessToken,wxUserInfoOnResultCallback);
    }
}
