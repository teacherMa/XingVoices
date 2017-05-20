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

    @Override
    public void downloadHeadPic(WxUserInfo info, OnResultCallback<ResponseBody> callback, ResponseBody body) {

        FileUtil.setUSER_FILE(info.getUnionid());

        String headPath = FileUtil.getUSER_FILE().concat("/").concat(Constants.FileName.HEAD_PIC)
                .concat("/" + info.getUnionid() + ".png");

        FileUtil.setUserHeadFile(headPath);

        File file = new File(headPath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                callback.onFail(Constants.ResponseError.UNKNOWN);
                return ;
            }
            return;
        }

        SaveHeadTask task = new SaveHeadTask(file,body,callback);
        task.execute();
    }

    public static class SaveHeadTask extends AsyncTask<Void,Void,Boolean>{

        private File mFile;
        private ResponseBody mBody;
        private OnResultCallback<ResponseBody> mCallback;

        public SaveHeadTask(File file, ResponseBody body, OnResultCallback<ResponseBody> callback) {
            mFile = file;
            mBody = body;
            mCallback = callback;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return savePic();
        }

        private Boolean savePic(){
            byte[] fileReader = new byte[4096];
            InputStream inputStream = mBody.byteStream();
            try {
                FileOutputStream outputStream = new FileOutputStream(mFile);
                while (true){
                    int read = inputStream.read(fileReader);
                    if(read == -1){
                        break;
                    }
                    outputStream.write(fileReader,0,read);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(null == mCallback){
                return;
            }
            if(aBoolean){
                mCallback.onSuccess(mBody,Constants.ResultCode.LOCAL);
                return;
            }
            mCallback.onFail(Constants.ResponseError.UNKNOWN);
        }
    }

    private static class SingletonHolder {
        private static final WXEntryLocalDS INSTANCE = new WXEntryLocalDS();
    }

}
