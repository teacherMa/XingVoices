package com.example.xiaomage.xingvoices.event;

/**
 * Created by xiaomage on 2017/5/7.
 */

public class MenuCloseEvent extends EmptyEvent {
    private boolean mIsClosed;

    public MenuCloseEvent(boolean isClosed) {
        mIsClosed = isClosed;
    }

    public boolean isClosed() {
        return mIsClosed;
    }
}
