package com.example.xiaomage.xingvoices.feature.record.publish;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.upload.UploadResp;

public class PublishContract {

    interface View extends BaseViewApi<Presenter> {
        void uploadSuccess(UploadResp resp);
        void publishSuccess(boolean success);
    }

    interface Presenter extends BasePresenterApi {
        void audition(String path);
        void upload(String voicePath,String originPicPath,String cropPicPath);
        void publishRecord(String title,String recordPath,int length,String cropPic,String originPic);
    }
}
