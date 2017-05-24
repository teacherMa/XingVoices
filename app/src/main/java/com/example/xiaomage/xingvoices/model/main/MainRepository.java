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
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.FileUtil;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.ResponseBody;

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

    public static MainRepository getInstance(@NonNull MainDataSource localDS,
                                             @NonNull MainDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new MainRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }

    @Override
    public void login(final OnResultCallback<XingVoiceUserResp> resultCallback, final WxUserInfo info,
                      XingVoiceUserResp xingVoiceUserResp) {
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
    public void getUserInfo(final OnResultCallback<BasicUserInfo> resultCallback, final String uid,
                            final String cid) {

        final OnResultCallback<BasicUserInfo> callback = new OnResultCallback<BasicUserInfo>() {
            @Override
            public void onSuccess(BasicUserInfo resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };

        if(null == uid){
            mLocalDS.getLocalUser(new OnResultCallback<XingVoiceUser>() {
                @Override
                public void onSuccess(XingVoiceUser resultValue, int code) {
                    mRemoteDS.getUserInfo(callback,resultValue.getUid(),cid);
                }

                @Override
                public void onFail(String errorMessage) {
                    resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
                }
            });
            return;
        }

        mRemoteDS.getUserInfo(callback,uid,cid);
    }

    @Override
    public void getLocalUser(final OnResultCallback<XingVoiceUser> callback) {
        OnResultCallback<XingVoiceUser> resultCallback = new OnResultCallback<XingVoiceUser>() {
            @Override
            public void onSuccess(XingVoiceUser resultValue, int code) {
                callback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                callback.onFail(errorMessage);
            }
        };
        mLocalDS.getLocalUser(resultCallback);
    }

    @Override
    public void requestPopularVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback,
                                         final String uid, final int is_u, final String cid,
                                         final int page, final int num) {
        final OnResultCallback<List<RemoteVoice>> callback = new OnResultCallback<List<RemoteVoice>>() {
            @Override
            public void onSuccess(List<RemoteVoice> resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }
            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };

        if(1 == is_u && null == cid){
            mLocalDS.getLocalUser(new OnResultCallback<XingVoiceUser>() {
                @Override
                public void onSuccess(XingVoiceUser resultValue, int code) {
                    mRemoteDS.requestPopularVoicesList(callback,uid,is_u,resultValue.getUid(),page,num);
                }

                @Override
                public void onFail(String errorMessage) {

                }
            });
            return;
        }

        mRemoteDS.requestPopularVoicesList(callback,uid,is_u,cid,page,num);
    }

    @Override
    public void requestComment(final OnResultCallback<List<CommentBean>> resultCallback,
                               final RemoteVoice voice, XingVoiceUser bean, final int commentType) {
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

    @Override
    public void downloadVoice(final OnResultCallback<ResponseBody> resultCallback,
                              ResponseBody responseBody, final String vUrl, final String cId) {

        if(null != FileUtil.getVoicePath(cId)){
            resultCallback.onSuccess(null,Constants.ResultCode.LOCAL);
            return;
        }

        OnResultCallback<ResponseBody> onResultCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                mLocalDS.downloadVoice(resultCallback,resultValue,vUrl,cId);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.downloadVoice(onResultCallback,responseBody,vUrl,cId);
    }

    @Override
    public void playVoice(final OnResultCallback<Boolean> resultCallback, String vId) {
        OnResultCallback<Boolean> onResultCallback = new OnResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.playVoice(onResultCallback,vId);
    }
}
