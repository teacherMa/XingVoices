package com.example.xiaomage.xingvoices.feature.main.menu.systemMessage;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.myVoiceCommentResp.MyVoiceCommentResp;

import java.util.List;

public class MessageContract {

    interface View extends BaseViewApi<Presenter> {
        void requestSuccess(List<MyVoiceCommentResp> resp);
    }

    interface Presenter extends BasePresenterApi {
        void requestComment(int num);
    }
}
