package com.example.xiaomage.xingvoices.model.record;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.framework.BaseRepository;
import com.example.xiaomage.xingvoices.model.bean.uploadResp.UploadResp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RecordRepository extends BaseRepository implements RecordDataSource {
    private static Lock sLock = new ReentrantLock();
    private static RecordRepository INSTANCE = null;
    private final RecordDataSource mLocalDS;
    private final RecordDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private RecordRepository(@NonNull RecordDataSource localDS, @NonNull RecordDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static RecordRepository getInstance(@NonNull RecordDataSource localDS, @NonNull RecordDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new RecordRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }

    @Override
    public void recordAudio(final OnResultCallback<String> resultCallback, boolean toStart) {
        OnResultCallback<String> callback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.recordAudio(callback,toStart);
    }

    @Override
    public void deleteAudio(final OnResultCallback<Boolean> resultCallback, String file) {
        OnResultCallback<Boolean> callback = new OnResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.deleteAudio(callback,file);
    }

    @Override
    public void audition(final OnResultCallback<Integer> resultCallback, String path) {
        OnResultCallback<Integer> onResultCallback = new OnResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mLocalDS.audition(onResultCallback,path);
    }

    @Override
    public void upload(final OnResultCallback<UploadResp> resultCallback, String voicePath,
                       String originPicPath, String cropPicPath) {
        OnResultCallback<UploadResp> onResultCallback = new OnResultCallback<UploadResp>() {
            @Override
            public void onSuccess(UploadResp resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.upload(onResultCallback,voicePath,originPicPath,cropPicPath);
    }

    @Override
    public void publish(final OnResultCallback<String> resultCallback, String title, String recordPath,
                        int length, String cropPic, String originPic) {
        OnResultCallback<String> onResultCallback = new OnResultCallback<String>() {
            @Override
            public void onSuccess(String resultValue, int code) {
                resultCallback.onSuccess(resultValue,code);
            }

            @Override
            public void onFail(String errorMessage) {
                resultCallback.onFail(errorMessage);
            }
        };
        mRemoteDS.publish(onResultCallback,title,recordPath,length,cropPic,originPic);
    }
}
