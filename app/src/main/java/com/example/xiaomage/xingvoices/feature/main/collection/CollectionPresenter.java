package com.example.xiaomage.xingvoices.feature.main.collection;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice;
import com.example.xiaomage.xingvoices.model.main.MainRepository;

import java.util.ArrayList;
import java.util.List;

public class CollectionPresenter extends BasePresenter<CollectionContract.View, MainRepository> implements CollectionContract.Presenter {

    public CollectionPresenter(@NonNull MainRepository repository, @NonNull CollectionContract.View view) {
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
