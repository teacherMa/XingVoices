package com.example.xiaomage.xingvoices.model.record;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.upload.UploadResp;

public interface RecordDataSource {
    void recordAudio(OnResultCallback<String> resultCallback, boolean toStart);
    void deleteAudio(OnResultCallback<Boolean> resultCallback,String path);
    void audition(OnResultCallback<Integer> resultCallback,String path);
    void upload(OnResultCallback<UploadResp> resultCallback, String voicePath,
                String originPicPath, String cropPicPath);
    void publish(OnResultCallback<String> resultCallback,String title, String recordPath,
                 int length, String cropPic, String originPic);
}
