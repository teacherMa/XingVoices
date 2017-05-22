package com.example.xiaomage.xingvoices.event;

import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;

/**
 * Created by xiaomage on 2017/5/19.
 *
 */

public class MainViewInitEvent extends EmptyEvent{
    private boolean mNeedInit;

    public MainViewInitEvent(boolean needInit) {
        mNeedInit = needInit;
    }

    public boolean isNeedInit() {
        return mNeedInit;
    }

    public void setNeedInit(boolean needInit) {
        mNeedInit = needInit;
    }
}
