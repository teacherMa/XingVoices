package com.example.xiaomage.xingvoices.feature.record;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.record.RecordRepository;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

public class RecordPresenter extends BasePresenter<RecordContract.View, RecordRepository> implements RecordContract.Presenter {

    public RecordPresenter(@NonNull RecordRepository repository, @NonNull RecordContract.View view) {
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
    public void deleteAudio(String file) {
        OnResultCallback<Boolean> onResultCallback = new OnResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().deleteSuccess();
            }

            @Override
            public void onFail(String errorMessage) {
                if(null == getView()){
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().deleteAudio(onResultCallback,file);
    }

    @Override
    public void audition(String path) {
        OnResultCallback<Integer> onResultCallback = new OnResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer resultValue, int code) {
                if(null == getView()){
                    return;
                }
                getView().isAudition(resultValue);
            }

            @Override
            public void onFail(String errorMessage) {
                if (getView() == null) {
                    return;
                }
                BaseUtil.showToast(errorMessage);
            }
        };
        getRepository().audition(onResultCallback,path);
    }
}
