package com.example.xiaomage.xingvoices.api;

import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constants.WxApi.ACCESS_TOKEN)
    Call<AccessToken> getAccessToken(@Query(Constants.RequestParam.APP_ID) String appId,
                                     @Query(Constants.RequestParam.APP_SECRET) String secret,
                                     @Query(Constants.RequestParam.CODE) String code,
                                     @Query(Constants.RequestParam.GRANT_TYPE) String type);

    @GET(Constants.WxApi.USER_INFO)
    Call<WxUserInfo> getUserInfo(@Query(Constants.RequestParam.ACCESS_TOKEN) String accessToken,
                                 @Query(Constants.RequestParam.OPEN_ID) String openId);

}
