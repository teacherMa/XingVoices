package com.example.xiaomage.xingvoices.feature.main.collection;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice;

import java.util.List;

public class CollectionContract {

    interface View extends BaseViewApi<Presenter> {
        void loadData(List<RemoteVoice> data);
    }

    interface Presenter extends BasePresenterApi {
        void requestData();
    }
}
