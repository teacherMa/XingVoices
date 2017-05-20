package com.example.xiaomage.xingvoices.model.bean.User;

/**
 * Created by xiaomage on 2017/5/20.
 */

public class UserInfo {
    /**
     * nickname : 萌萌哒的probe
     * headpic : http://www.starsound.xyz/yuliao/public/uploads/
     * guanzhu : 0
     * fensi : 0
     */

    private String nickname;
    private String headpic;
    private int guanzhu;
    private int fensi;

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

    public int getGuanzhu() {
        return guanzhu;
    }

    public void setGuanzhu(int guanzhu) {
        this.guanzhu = guanzhu;
    }

    public int getFensi() {
        return fensi;
    }

    public void setFensi(int fensi) {
        this.fensi = fensi;
    }
}
