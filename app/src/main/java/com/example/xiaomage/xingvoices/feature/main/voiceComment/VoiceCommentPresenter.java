package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

public class VoiceCommentPresenter extends BasePresenter<VoiceCommentContract.View, MainRepository> implements VoiceCommentContract.Presenter {

    public VoiceCommentPresenter(@NonNull MainRepository repository, @NonNull VoiceCommentContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestComment(RemoteVoice voice) {

    }
}
