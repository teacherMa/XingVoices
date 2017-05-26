package com.example.xiaomage.xingvoices.event;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class ShowNewMessage extends EmptyEvent {
    boolean mPublishNew;

    public void setPublishNew(boolean publishNew) {
        mPublishNew = publishNew;
    }

    public boolean isPublishNew() {

        return mPublishNew;
    }

    public ShowNewMessage(boolean publishNew) {

        mPublishNew = publishNew;
    }
}
