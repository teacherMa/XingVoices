package com.example.xiaomage.xingvoices.model.bean.Resp.likeCommentResp;

/**
 * Created by xiaomage on 2017/5/25.
 */

public class LikeItResp {
    /**
     * status : -1
     * info : 你已经点过赞了
     */

    private int status;
    private String info;

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
}
