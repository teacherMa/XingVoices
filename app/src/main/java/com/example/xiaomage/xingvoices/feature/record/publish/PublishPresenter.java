package com.example.xiaomage.xingvoices.feature.record.publish;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.record.RecordRepository;

public class PublishPresenter extends BasePresenter<PublishContract.View, RecordRepository> implements PublishContract.Presenter {

    public PublishPresenter(@NonNull RecordRepository repository, @NonNull PublishContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
