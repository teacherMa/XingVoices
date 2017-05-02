package com.example.xiaomage.xingvoices.model.record;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BaseRepository;

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
}
