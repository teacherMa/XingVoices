package com.example.xiaomage.xingvoices.model.bean.Resp.shieldResp;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class ShieldResp {
    /**
     * status : 1
     * info : 当前语音屏蔽成功
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