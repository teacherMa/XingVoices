package com.example.xiaomage.xingvoices.model.record.remote;

import com.example.xiaomage.xingvoices.model.record.RecordDataSource;

public class RecordRemoteDS implements RecordDataSource {
    private RecordRemoteDS() {
    }

    public static RecordRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RecordRemoteDS INSTANCE = new RecordRemoteDS();
    }
}
