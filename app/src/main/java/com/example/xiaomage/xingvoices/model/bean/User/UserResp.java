package com.example.xiaomage.xingvoices.model.bean.User;

/**
 * Created by xiaomage on 2017/5/19.
 */

public class UserResp {

    /**
     * status : 1
     * info : 成功
     * user : {"uid":"TzFiVA7MIl906N","wxopenid":"o3-zWw9yiNAsprKWA_U9AVXJEsBM","nickname":"萌萌哒的probe","headpic":"http://www.starsound.xyz/yuliao/public/uploads/","sex":1}
     */

    private int status;
    private String info;
    private UserBean user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
