package com.example.xiaomage.xingvoices.model.wxapi.local;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.WxBean.AccessToken;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.wxapi.WXEntryDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.ResponseBody;

public class WXEntryLocalDS implements WXEntryDataSource {
    private WXEntryLocalDS() {
    }

    public static WXEntryLocalDS getInstance() {
        return WXEntryLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void getAccessToken(String code, OnResultCallback<AccessToken> callback) {

    }

    @Override
    public void getWxUserInfo(AccessToken accessToken, OnResultCallback<WxUserInfo> callback) {

    }

    private static class SingletonHolder {
        private static final WXEntryLocalDS INSTANCE = new WXEntryLocalDS();
    }

}
