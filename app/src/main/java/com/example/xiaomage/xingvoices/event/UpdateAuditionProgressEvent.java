package com.example.xiaomage.xingvoices.event;

/**
 * Created by xiaomage on 2017/5/24.
 */

public class UpdateAuditionProgressEvent extends EmptyEvent {
    private int mCurPosition;

    public UpdateAuditionProgressEvent(int curPosition) {
        mCurPosition = curPosition;
    }

    public void setCurPosition(int curPosition) {
        mCurPosition = curPosition;
    }

    public int getCurPosition() {

        return mCurPosition;
    }
}
