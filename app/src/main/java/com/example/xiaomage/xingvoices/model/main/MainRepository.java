package com.example.xiaomage.xingvoices.model.main;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BaseRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainRepository extends BaseRepository implements MainDataSource {
    private static Lock sLock = new ReentrantLock();
    private static MainRepository INSTANCE = null;
    private final MainDataSource mLocalDS;
    private final MainDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private MainRepository(@NonNull MainDataSource localDS, @NonNull MainDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static MainRepository getInstance(@NonNull MainDataSource localDS, @NonNull MainDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new MainRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }
}
