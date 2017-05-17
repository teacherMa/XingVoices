package com.example.xiaomage.xingvoices.model.wxapi;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BaseRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WXEntryRepository extends BaseRepository implements WXEntryDataSource {
    private static Lock sLock = new ReentrantLock();
    private static WXEntryRepository INSTANCE = null;
    private final WXEntryDataSource mLocalDS;
    private final WXEntryDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private WXEntryRepository(@NonNull WXEntryDataSource localDS, @NonNull WXEntryDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static WXEntryRepository getInstance(@NonNull WXEntryDataSource localDS, @NonNull WXEntryDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new WXEntryRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }
}
