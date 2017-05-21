package com.example.xiaomage.xingvoices.model.bean.User;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by xiaomage on 2017/5/8.
 */

public class UserBean extends RealmObject implements Serializable {

    /**
     * uid : TzFiVA7MIl906N
     * wxopenid : o3-zWw9yiNAsprKWA_U9AVXJEsBM
     * nickname : 萌萌哒的probe
     * headpic : http://www.starsound.xyz/yuliao/public/uploads/
     * sex : 1
     */

    private String uid;
    private String wxopenid;
    private String nickname;
    private String headpic;
    private int sex;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
