package com.example.xiaomage.xingvoices.model.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BaseRepository;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.Resp.shieldResp.ShieldResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.bean.Resp.collectionResp.CollectionResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.publishCommentResp.CommentResp;
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
                mLocalDS.login(resultCallback, info, resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.login(callback, info, null);
    }

    @Override
    public void getUserInfo(final OnResultCallback<BasicUserInfo> resultCallback, final String uid,
                            final String cid) {

        final OnResultCallback<BasicUserInfo> callback = new OnResultCallback<BasicUserInfo>() {
            @Override
            public void onSuccess(BasicUserInfo resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };

        if (null == uid) {
            mLocalDS.getLocalUser(new OnResultCallback<XingVoiceUser>() {
                @Override
                public void onSuccess(XingVoiceUser resultValue, int code) {
                    mRemoteDS.getUserInfo(callback, resultValue.getUid(), cid);
                }

                @Override
                public void onFail(String errorMessage) {
                    resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
                }
            });
            return;
        }

        mRemoteDS.getUserInfo(callback, uid, cid);
    }

    @Override
    public void getLocalUser(final OnResultCallback<XingVoiceUser> callback) {
        OnResultCallback<XingVoiceUser> resultCallback = new OnResultCallback<XingVoiceUser>() {
            @Override
            public void onSuccess(XingVoiceUser resultValue, int code) {
                callback.onSuccess(resultValue, code);
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
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };

        if (1 == is_u && null == cid) {
            mLocalDS.getLocalUser(new OnResultCallback<XingVoiceUser>() {
                @Override
                public void onSuccess(XingVoiceUser resultValue, int code) {
                    mRemoteDS.requestPopularVoicesList(callback, uid, is_u, resultValue.getUid(), page, num);
                }

                @Override
                public void onFail(String errorMessage) {

                }
            });
            return;
        }

        mRemoteDS.requestPopularVoicesList(callback, uid, is_u, cid, page, num);
    }

    @Override
    public void requestCollectionVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback, int num) {
        OnResultCallback<List<RemoteVoice>> onResultCallback = new OnResultCallback<List<RemoteVoice>>() {
            @Override
            public void onSuccess(List<RemoteVoice> resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.requestCollectionVoicesList(onResultCallback, num * 10);
    }

    @Override
    public void requestFollowVoicesList(final OnResultCallback<List<RemoteVoice>> resultCallback, int num) {
        OnResultCallback<List<RemoteVoice>> onResultCallback = new OnResultCallback<List<RemoteVoice>>() {
            @Override
            public void onSuccess(List<RemoteVoice> resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.requestFollowVoicesList(onResultCallback, num * 10);
    }

    @Override
    public void requestComment(final OnResultCallback<List<CommentBean>> resultCallback,
                               final RemoteVoice voice, XingVoiceUser bean, final int commentType, final int num) {
        OnResultCallback<XingVoiceUser> loackCallback = new OnResultCallback<XingVoiceUser>() {
            @Override
            public void onSuccess(XingVoiceUser resultValue, int code) {
                mRemoteDS.requestComment(resultCallback, voice, resultValue, commentType,num);
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

        if (null != FileUtil.getVoicePath(cId)) {
            resultCallback.onSuccess(null, Constants.ResultCode.LOCAL);
            return;
        }

        OnResultCallback<ResponseBody> onResultCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                mLocalDS.downloadVoice(resultCallback, resultValue, vUrl, cId);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.downloadVoice(onResultCallback, responseBody, vUrl, cId);
    }

    @Override
    public void downloadHeadPic(final OnResultCallback<ResponseBody> resultCallback, ResponseBody responseBody,
                                final String url) {
        OnResultCallback<ResponseBody> onResultCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                //然后本地进行下载
                mLocalDS.downloadHeadPic(resultCallback,resultValue,url);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        //先进行远端请求，获得responseBody
        mRemoteDS.downloadHeadPic(onResultCallback,responseBody,url);
    }

    @Override
    public void playVoice(final OnResultCallback<Boolean> resultCallback, String vId) {
        OnResultCallback<Boolean> onResultCallback = new OnResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.playVoice(onResultCallback, vId);
    }

    @Override
    public void likeIt(final OnResultCallback<String> resultCallback, String cId) {
        OnResultCallback<String> onResultCallback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.likeIt(onResultCallback, cId);
    }


    @Override
    public void playVoiceCom(final OnResultCallback<Boolean> resultCallback, final CommentBean commentBean) {

        //如果已经下载好了，直接播放
        if (null != FileUtil.getVoicePath(commentBean.getCid())) {
            mLocalDS.playVoice(resultCallback, commentBean.getCid());
            return;
        }

        final OnResultCallback<ResponseBody> localDownloadOnResultCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                //本地下载成功之后进行播放
                mLocalDS.playVoice(resultCallback, commentBean.getCid());
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };

        //否则先请求远端下载
        mRemoteDS.downloadVoice(new OnResultCallback<ResponseBody>() {
                                    @Override
                                    public void onSuccess(ResponseBody resultValue, int code) {
                                        //成功之后请求本地下载
                                        mLocalDS.downloadVoice(localDownloadOnResultCallback, resultValue,
                                                commentBean.getContent(), commentBean.getCid());
                                    }

                                    @Override
                                    public void onFail(String errorMessage) {
                                        resultCallback.onFail(errorMessage);
                                    }
                                },
                null, commentBean.getContent(), commentBean.getCid());
    }

    @Override
    public void recordAudio(final OnResultCallback<String> resultCallback, boolean toStart) {
        OnResultCallback<String> callback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.recordAudio(callback, toStart);
    }

    @Override
    public void publishTextCom(final OnResultCallback<CommentResp> resultCallback, String vId, String content) {
        OnResultCallback<CommentResp> callback = new OnResultCallback<CommentResp>() {
            @Override
            public void onSuccess(CommentResp resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.publishTextCom(callback, vId, content);
    }

    @Override
    public void publishVoiceCom(final OnResultCallback<CommentResp> resultCallback, String vId, String cId, int cLength) {
        OnResultCallback<CommentResp> callback = new OnResultCallback<CommentResp>() {
            @Override
            public void onSuccess(CommentResp resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.publishVoiceCom(callback, vId, cId, cLength);
    }

    @Override
    public void uploadHeadPic(OnResultCallback<UploadResp> resultCallback) {
//        mRemoteDS.uploadHeadPic(resultCallback);
    }

    @Override
    public void changeFollowState(final OnResultCallback<FollowResp> resultCallback, String cid, int state) {
        OnResultCallback<FollowResp> callback = new OnResultCallback<FollowResp>() {
            @Override
            public void onSuccess(FollowResp resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.changeFollowState(callback, cid, state);
    }

    @Override
    public void toCollection(final OnResultCallback<CollectionResp> resultCallback, String cid, int state) {
        OnResultCallback<CollectionResp> callback = new OnResultCallback<CollectionResp>() {
            @Override
            public void onSuccess(CollectionResp resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.toCollection(callback, cid, state);
    }

    @Override
    public void requestMyVoiceComments(final OnResultCallback<List<MyVoiceCommentResp>> resultCallback, int num) {
        OnResultCallback<List<MyVoiceCommentResp>> callback = new OnResultCallback<List<MyVoiceCommentResp>>() {
            @Override
            public void onSuccess(List<MyVoiceCommentResp> resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.requestMyVoiceComments(callback, num);
    }

    @Override
    public void shieldVoice(final OnResultCallback<ShieldResp> resultCallback, String vid) {
        OnResultCallback<ShieldResp> onResultCallback = new OnResultCallback<ShieldResp>() {
            @Override
            public void onSuccess(ShieldResp resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.shieldVoice(onResultCallback, vid);
    }

    @Override
    public void stopPlayVoice(final OnResultCallback<String> resultCallback) {
        OnResultCallback<String> onResultCallback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                resultCallback.onSuccess(resultValue, code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.stopPlayVoice(onResultCallback);
    }
}
