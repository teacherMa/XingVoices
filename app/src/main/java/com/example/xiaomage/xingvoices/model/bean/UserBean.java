package com.example.xiaomage.xingvoices.model.bean;

import java.io.Serializable;

/**
 * Created by xiaomage on 2017/5/8.
 */

public class UserBean implements Serializable {
    boolean mIsUser;

    public void setUser(boolean user) {
        mIsUser = user;
    }

    public boolean isUser() {

        return mIsUser;
    }
}
