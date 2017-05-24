package com.example.xiaomage.xingvoices.event;

/**
 * Created by xiaomage on 2017/5/23.
 */

public class ChangeAnimEvent extends EmptyEvent {
    private int position;
    private boolean mShouldStart;

    public ChangeAnimEvent(){}

    public ChangeAnimEvent(int position, boolean shouldStart) {
        this.position = position;
        mShouldStart = shouldStart;
    }

    public int getPosition() {
        return position;
    }

    public boolean isShouldStart() {
        return mShouldStart;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setShouldStart(boolean shouldStart) {
        mShouldStart = shouldStart;
    }
}
