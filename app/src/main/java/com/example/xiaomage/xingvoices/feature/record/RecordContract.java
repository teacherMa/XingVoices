package com.example.xiaomage.xingvoices.feature.record;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;

public class RecordContract {

    interface View extends BaseViewApi<Presenter> {
        void recordSuccess(String path);
        void deleteSuccess();
        void isAudition(Integer length);
    }

    interface Presenter extends BasePresenterApi {
        void recordAudio(boolean toStart);
        void deleteAudio(String file);
        void audition(String path);
    }
}
