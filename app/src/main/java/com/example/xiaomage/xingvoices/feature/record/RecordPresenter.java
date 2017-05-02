package com.example.xiaomage.xingvoices.feature.record;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.record.RecordRepository;

public class RecordPresenter extends BasePresenter<RecordContract.View, RecordRepository> implements RecordContract.Presenter {

    public RecordPresenter(@NonNull RecordRepository repository, @NonNull RecordContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
