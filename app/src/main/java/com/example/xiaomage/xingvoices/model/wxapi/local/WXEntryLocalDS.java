package com.example.xiaomage.xingvoices.model.wxapi.local;

import com.example.xiaomage.xingvoices.model.wxapi.WXEntryDataSource;

public class WXEntryLocalDS implements WXEntryDataSource {
    private WXEntryLocalDS() {
    }

    public static WXEntryLocalDS getInstance() {
        return WXEntryLocalDS.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final WXEntryLocalDS INSTANCE = new WXEntryLocalDS();
    }
}
