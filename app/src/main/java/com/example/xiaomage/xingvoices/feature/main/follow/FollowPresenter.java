package com.example.xiaomage.xingvoices.feature.main.follow;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

import java.util.ArrayList;
import java.util.List;

public class FollowPresenter extends BasePresenter<FollowContract.View, MainRepository> implements FollowContract.Presenter {

    public FollowPresenter(@NonNull MainRepository repository, @NonNull FollowContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        requestData();
    }

    @Override
    public void requestData() {
        List<RemoteVoice> voices = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            voices.add(new RemoteVoice());
            voices.add(new RemoteVoice());
        }
        getView().loadData(voices);
    }
}
