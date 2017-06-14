package com.example.xiaomage.xingvoices.event.VH;

import com.example.xiaomage.xingvoices.event.EmptyEvent;

/**
 * Created by xiaomage on 2017/5/25.
 */

public class VHRecordEvent extends EmptyEvent {
    String mTag;
    boolean mToStart;

    public void setTag(String tag) {
        mTag = tag;
    }

    public void setToStart(boolean toStart) {
        mToStart = toStart;
    }

    public String getTag() {

        return mTag;
    }

    public boolean isToStart() {
        return mToStart;
    }

    public VHRecordEvent(String tag, boolean toStart) {

        mTag = tag;
        mToStart = toStart;
    }
}
