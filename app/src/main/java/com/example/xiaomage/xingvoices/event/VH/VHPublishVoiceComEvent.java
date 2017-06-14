package com.example.xiaomage.xingvoices.event.VH;

import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class VHPublishVoiceComEvent extends EmptyEvent {
    private String mTag;
    private RemoteVoice mRemoteVoice;
    private String mComLength;

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getTag() {

        return mTag;
    }

    public VHPublishVoiceComEvent(String tag, RemoteVoice remoteVoice, String comLength) {
        mTag = tag;
        mRemoteVoice = remoteVoice;
        mComLength = comLength;
    }

    public VHPublishVoiceComEvent() {
    }

    public RemoteVoice getRemoteVoice() {

        return mRemoteVoice;
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {

        mRemoteVoice = remoteVoice;
    }

    public void setComLength(String comLength) {
        mComLength = comLength;
    }

    public String getComLength() {

        return mComLength;
    }
}
