package com.example.xiaomage.xingvoices.feature.main.collection;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.Resp.collectionResp.CollectionResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.publishCommentResp.CommentResp;
import com.example.xiaomage.xingvoices.model.bean.Resp.shieldResp.ShieldResp;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class CollectionPresenter extends BasePresenter<CollectionContract.View, MainRepository> implements CollectionContract.Presenter {

    public CollectionPresenter(@NonNull MainRepository repository, @NonNull CollectionContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void recordAudio(boolean toStart) {
        OnResultCallback<String> onResultCallback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().recordSuccess(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().recordAudio(onResultCallback,toStart);
    }

    @Override
    public void requestCollectionVoice(final int page) {
        final OnResultCallback<List<RemoteVoice>> callback = new OnResultCallback<List<RemoteVoice>>() {
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
        getRepository().requestCollectionVoicesList(callback,page);
    }

    @Override
    public void downloadVoice(final String vUrl, final String vId) {
        OnResultCallback<ResponseBody> resultCallback = new OnResultCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().downloadSuccess(vId);
            }

            @Override
            public void onFail(String errorMessage) {
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().downloadVoice(resultCallback,null,vUrl,vId);
    }

    @Override
    public void playVoice(String vId) {
        OnResultCallback<Boolean> resultCallback = new OnResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().playFinished();
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().playVoice(resultCallback,vId);
    }

    @Override
    public void publishTextCom(String vId, String content) {
        OnResultCallback<CommentResp> resultCallback = new OnResultCallback<CommentResp>() {
            @Override
            public void onSuccess(CommentResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().commentSuccess(resultValue.getInfo());
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().publishTextCom(resultCallback,vId,content);
    }

    @Override
    public void publishVoiceCom(String vId, String cId, int cLength) {
        OnResultCallback<CommentResp> resultCallback = new OnResultCallback<CommentResp>() {
            @Override
            public void onSuccess(CommentResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().commentSuccess(resultValue.getInfo());
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().publishVoiceCom(resultCallback,vId,cId,cLength);
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

    @Override
    public void toCollection(String vid, int state) {
        OnResultCallback<CollectionResp> onResultCallback = new OnResultCallback<CollectionResp>() {
            @Override
            public void onSuccess(CollectionResp resultValue, int code) {
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
        getRepository().toCollection(onResultCallback,vid,state);
    }

    @Override
    public void toShield(String vid) {
        OnResultCallback<ShieldResp> callback = new OnResultCallback<ShieldResp>() {
            @Override
            public void onSuccess(ShieldResp resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().shieldResult(resultValue.getInfo());
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().shieldVoice(callback,vid);
    }

    @Override
    public void toStopPlayVoice() {
        OnResultCallback<String> onResultCallback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                if (getView() == null) {
                    return;
                }
            }

            @Override
            public void onFail(String errorMessage) {
                if (getView() == null) {
                    return;
                }
            }
        };
        getRepository().stopPlayVoice(onResultCallback);
    }
}
