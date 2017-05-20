package com.example.xiaomage.xingvoices.model.bean.RemoteVoice;

/**
 * Created by xiaomage on 2017/5/20.
 */

public class Comment_Voice {

    /**
     * user : Elsa
     * headpic : http://wx.qlogo.cn/mmopen/dUyqaiaxg9oOdp4Zbql8P2Gicf6UaVZHf0bk27VbXDvvwm4BlibDnIcPyPKXsCMvW2B1Nzn7srvZP32jACReBcyDoib2EEn2YFIk/0
     * zan : 1
     * cid : d1jDH7Ouzop
     * is_zan : 0
     * clength : 5
     * content : http://app.starsound.xyz/user/qnskIbhlcB38/file/20170417/fhecqq9g3b1i4xvi0tll.mp3
     */

    private String user;
    private String headpic;
    private int zan;
    private String cid;
    private int is_zan;
    private int clength;
    private String content;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getIs_zan() {
        return is_zan;
    }

    public void setIs_zan(int is_zan) {
        this.is_zan = is_zan;
    }

    public int getClength() {
        return clength;
    }

    public void setClength(int clength) {
        this.clength = clength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
