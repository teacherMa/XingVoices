package com.example.xiaomage.xingvoices.feature.main.comment.voiceComment;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

import okhttp3.ResponseBody;

public class VoiceCommentPresenter extends BasePresenter<VoiceCommentContract.View, MainRepository> implements VoiceCommentContract.Presenter {

    public VoiceCommentPresenter(@NonNull MainRepository repository, @NonNull VoiceCommentContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        RemoteVoice remoteVoice = getView().getRemoteVoice();
        if(null == remoteVoice){
            return;
        }
        requestComment(remoteVoice,20);
    }

    @Override
    public void requestComment(RemoteVoice voice,int num) {
        OnResultCallback<List<CommentBean>> callback = new OnResultCallback<List<CommentBean>>() {
            @Override
            public void onSuccess(List<CommentBean> resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().updateData(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().requestComment(callback,voice,null, Constants.CommentType.VOICE,num);
    }

    @Override
    public void likeIt(String cId) {
        OnResultCallback<String> callback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().likeSuccess(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().likeIt(callback,cId);
    }

    @Override
    public void playVoiceCom(CommentBean commentBean) {
        OnResultCallback<Boolean> callback = new OnResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resultValue, int code) {
                if (getView() == null) {
                    return;
                }
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().playVoiceCom(callback,commentBean);
    }
}
