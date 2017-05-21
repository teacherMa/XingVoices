package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;

public class VoiceCommentPresenter extends BasePresenter<VoiceCommentContract.View , TargetRepository> implements VoiceCommentContract.Presenter {
    
    public VoiceCommentPresenter(@NonNull TargetRepository repository, @NonNull VoiceCommentContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        
    }
}
