package com.example.xiaomage.xingvoices.feature.main.popular;

import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.framework.BaseViewApi;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;

import java.util.List;

public class PopularContract {

    interface View extends BaseViewApi<Presenter> {
        void loadData(List<RemoteVoice> data);
        void downloadSuccess(String vId);
        void playFinished();
        void recordSuccess(String path);
        void commentSuccess(String info);
        void changeStateSuccess(String info);
        void shieldResult(String info);
    }

    interface Presenter extends BasePresenterApi {
        void recordAudio(boolean toStart);
        void requestAllPopularVoice(int page);
        void downloadVoice(String vUrl,String vId);
        void playVoice(String vId);

        void publishTextCom(String vId,String content);

        /**
         * @param cId 评论在本地存储的id
         * @param vId 被评论的语音id，在DS中进行转换
         * @param cLength 评论长短*/
        void publishVoiceCom(String vId,String cId,int cLength);

        void changeFollowState(String cid,int state);

        void toCollection(String vid,int state);

        void toShield(String vid);

        void toStopPlayVoice();
    }
}
