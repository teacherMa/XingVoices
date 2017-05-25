package com.example.xiaomage.xingvoices.event;

/**
 * Created by xiaomage on 2017/5/25.
 */

public class ToLikeComEvent extends EmptyEvent {
    String mId;
    String mTag;

    public ToLikeComEvent() {
    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {

        return mId;
    }

    public ToLikeComEvent(String id, String tag) {
        mId = id;
        mTag = tag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getTag() {

        return mTag;
    }
}
