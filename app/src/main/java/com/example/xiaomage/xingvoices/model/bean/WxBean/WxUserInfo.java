package com.example.xiaomage.xingvoices.model.bean.WxBean;

import java.io.Serializable;
import java.util.List;

import me.shaohui.shareutil.login.result.BaseUser;

/**
 * Created by xiaomage on 2017/5/18.
 */

public class WxUserInfo implements Serializable{
    /**
     * openid : o3-zWw9yiNAsprKWA_U9AVXJEsBM
     * nickname : 萌萌哒的probe
     * sex : 1
     * language : zh_CN
     * city : Chengdu
     * province : Sichuan
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/hBbcYzssXHvIROv3xVLLTicsn8LNvn4OJZQ52icWicBvuux7jD98kg8rru7OTI89LIUfuCKS2u3CiahS9j6rgIayNKYbDW0b3knib/0
     * privilege : []
     * unionid : oen5gv3FFXeajbagbf9FYbcJJkZk
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
