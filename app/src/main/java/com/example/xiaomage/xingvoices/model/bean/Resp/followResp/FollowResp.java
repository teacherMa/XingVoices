package com.example.xiaomage.xingvoices.model.bean.Resp.followResp;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class FollowResp {
    /**
     * status : 1
     * res : 0
     * info : 取消关注成功
     */

    private int status;
    private String res;
    private String info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
