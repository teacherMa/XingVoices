package com.example.xiaomage.xingvoices.event;

import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;

/**
 * Created by xiaomage on 2017/5/19.
 */

public class MainViewInitEvent extends EmptyEvent{
    private XingVoiceUserResp mResp;

    public MainViewInitEvent(XingVoiceUserResp resp) {
        mResp = resp;
    }

    public XingVoiceUserResp getResp() {
        return mResp;
    }

    public void setResp(XingVoiceUserResp resp) {
        mResp = resp;
    }


}
