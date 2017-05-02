package com.example.xiaomage.xingvoices.model.record.local;

import com.example.xiaomage.xingvoices.model.record.RecordDataSource;

public class RecordLocalDS implements RecordDataSource {
    private RecordLocalDS() {
    }

    public static RecordLocalDS getInstance() {
        return RecordLocalDS.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RecordLocalDS INSTANCE = new RecordLocalDS();
    }
}
