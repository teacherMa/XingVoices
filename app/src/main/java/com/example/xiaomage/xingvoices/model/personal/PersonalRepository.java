package com.example.xiaomage.xingvoices.model.personal;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BaseRepository;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PersonalRepository extends BaseRepository implements PersonalDataSource {
    private static Lock sLock = new ReentrantLock();
    private static PersonalRepository INSTANCE = null;
    private final PersonalDataSource mLocalDS;
    private final PersonalDataSource mRemoteDS;
    private boolean mIsFirstLoad;

    private PersonalRepository(@NonNull PersonalDataSource localDS, @NonNull PersonalDataSource remoteDS) {
        mLocalDS = localDS;
        mRemoteDS = remoteDS;
    }

    public static PersonalRepository getInstance(@NonNull PersonalDataSource localDS, @NonNull PersonalDataSource remoteDS) {
        sLock.lock();
        if (null == INSTANCE) {
            INSTANCE = new PersonalRepository(localDS, remoteDS);
        }
        sLock.unlock();

        return INSTANCE;
    }
}
