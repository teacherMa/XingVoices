package com.example.xiaomage.xingvoices.feature.main.textComment;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

import java.util.List;

public class TextCommentContract {

    interface View extends BaseViewApi<Presenter> {
        RemoteVoice getRemoteVoice();
        void updateData(List<CommentBean> been);
    }

    interface Presenter extends BasePresenterApi {
        void requestComment(RemoteVoice voice);
    }
}
