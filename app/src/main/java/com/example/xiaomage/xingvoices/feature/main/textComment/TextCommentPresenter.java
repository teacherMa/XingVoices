package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

public class TextCommentPresenter extends BasePresenter<TextCommentContract.View, MainRepository> implements TextCommentContract.Presenter {

    public TextCommentPresenter(@NonNull MainRepository repository, @NonNull TextCommentContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        RemoteVoice remoteVoice = getView().getRemoteVoice();
        if(null == remoteVoice){
            return;
        }
        requestComment(remoteVoice);
    }

    @Override
    public void requestComment(RemoteVoice voice) {
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
        getRepository().requestComment(callback,voice,null, Constants.CommentType.TEXT);
    }
}
