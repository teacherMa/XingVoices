package com.example.xiaomage.xingvoices.model.bean.WxBean;

/**
 * Created by xiaomage on 2017/5/18.
 */

public class AccessToken {
    /**
     * access_token : Mz1XZxF6Qo2eMvUn0SSMHz3NYPhLAVCBSCqslTso9xe99wxg_GWD7HBYyul6_ZE7_vjVaFCdYWyrLfFguv8f7jK6YezA2dk6nd8ww2Tz9Kg
     * expires_in : 7200
     * refresh_token : TOzXdJuJSDaQp2viYuGtvCifNQ8wGi0FPe9ScQlkAMnfoVBnrDWlVV2taMw-34OWzql8RZOFoKhWE2mgAxTAOpQ1M-oRFogeD2x639zEPPI
     * openid : o3-zWw9yiNAsprKWA_U9AVXJEsBM
     * scope : snsapi_userinfo
     * unionid : oen5gv3FFXeajbagbf9FYbcJJkZk
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

}
