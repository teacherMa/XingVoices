package com.example.xiaomage.xingvoices.event.VH;

import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class VHPublishTextComEvent extends EmptyEvent {
    String mTag;
    String mContent;
    RemoteVoice mRemoteVoice;

    public VHPublishTextComEvent(String tag, String content, RemoteVoice remoteVoice) {
        mTag = tag;
        mContent = content;
        mRemoteVoice = remoteVoice;
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    public RemoteVoice getRemoteVoice() {

        return mRemoteVoice;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTag() {
        return mTag;
    }

    public String getContent() {
        return mContent;
    }
}
