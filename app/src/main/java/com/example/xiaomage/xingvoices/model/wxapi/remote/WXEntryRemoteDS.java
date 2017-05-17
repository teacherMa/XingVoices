package com.example.xiaomage.xingvoices.model.wxapi.remote;

import com.example.xiaomage.xingvoices.model.wxapi.WXEntryDataSource;

public class WXEntryRemoteDS implements WXEntryDataSource {
    private WXEntryRemoteDS() {
    }

    public static WXEntryRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final WXEntryRemoteDS INSTANCE = new WXEntryRemoteDS();
    }
}
