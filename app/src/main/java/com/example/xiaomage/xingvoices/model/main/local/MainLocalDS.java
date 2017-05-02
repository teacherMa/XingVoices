package com.example.xiaomage.xingvoices.model.main.local;

import com.example.xiaomage.xingvoices.model.main.MainDataSource;

public class MainLocalDS implements MainDataSource {
    private MainLocalDS() {
    }

    public static MainLocalDS getInstance() {
        return MainLocalDS.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final MainLocalDS INSTANCE = new MainLocalDS();
    }
}
