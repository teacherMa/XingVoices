package com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

import java.util.List;

public class VoiceCommentContract {

    interface View extends BaseViewApi<Presenter> {
        RemoteVoice getRemoteVoice();
        void updateData(List<CommentBean> been);
        void likeSuccess(String info);

    }

    interface Presenter extends BasePresenterApi {
        void requestComment(RemoteVoice voice,int num);
        void likeIt(String cId);
        void playComVoice(CommentBean commentBean);
    }
}
