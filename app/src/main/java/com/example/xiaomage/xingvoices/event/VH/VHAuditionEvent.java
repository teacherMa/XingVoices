package com.example.xiaomage.xingvoices.event.VH;

import com.example.xiaomage.xingvoices.event.EmptyEvent;

/**
 * Created by xiaomage on 2017/5/25.
 */

public class VHAuditionEvent extends EmptyEvent {
    String mTag;

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getTag() {

        return mTag;
    }

    public VHAuditionEvent(String tag) {
        mTag = tag;
    }
}
