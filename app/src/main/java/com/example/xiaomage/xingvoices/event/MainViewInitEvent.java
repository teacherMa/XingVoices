package com.example.xiaomage.xingvoices.event;

import com.example.xiaomage.xingvoices.model.bean.User.UserResp;

/**
 * Created by xiaomage on 2017/5/19.
 */

public class MainViewInitEvent extends EmptyEvent{
    private UserResp mResp;

    public MainViewInitEvent(UserResp resp) {
        mResp = resp;
    }

    public UserResp getResp() {
        return mResp;
    }

    public void setResp(UserResp resp) {
        mResp = resp;
    }


}
