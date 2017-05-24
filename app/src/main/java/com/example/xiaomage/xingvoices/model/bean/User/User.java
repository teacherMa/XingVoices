package com.example.xiaomage.xingvoices.model.bean.User;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by peng on 2017/4/24.
 */

public class User extends RealmObject {
    @PrimaryKey
    private String mId;
    private String mName;
    private String mAvatar;
    private String mCookie;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getCookie() {
        return mCookie;
    }

    public void setCookie(String cookie) {
        mCookie = cookie;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
