package com.example.xiaomage.xingvoices.model.main.local;

import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.main.MainDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainLocalDS implements MainDataSource {
    private MainLocalDS() {
    }

    public static MainLocalDS getInstance() {
        return MainLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void login(OnResultCallback<XingVoiceUserResp> resultCallback, WxUserInfo info, XingVoiceUserResp xingVoiceUserResp) {

        Realm realm = Realm.getDefaultInstance();

        if(realm.where(XingVoiceUser.class).findAll().size()!=0){
            resultCallback.onSuccess(xingVoiceUserResp, Constants.ResultCode.LOCAL);
            return;
        }

        realm.beginTransaction();

        final XingVoiceUser resp = realm.copyToRealm(xingVoiceUserResp.getUser());

        realm.commitTransaction();

        resultCallback.onSuccess(xingVoiceUserResp,Constants.ResultCode.LOCAL);

    }

    @Override
    public void getUserInfo(OnResultCallback<BasicUserInfo> resultCallback, XingVoiceUserResp resp) {

    }

    @Override
    public void getLocalUser(OnResultCallback<XingVoiceUser> callback) {

        Realm realm = Realm.getDefaultInstance();

        List<XingVoiceUser> users = realm.where(XingVoiceUser.class).findAll();

        if(0 == users.size()){
            callback.onFail(Constants.ResponseError.DATA_EMPTY);
            return;
        }

        callback.onSuccess(users.get(0),Constants.ResultCode.LOCAL);
    }

    @Override
    public void requestVoicesList(OnResultCallback<List<RemoteVoice>> resultCallback, XingVoiceUserResp resp, String dataType) {

    }

    @Override
    public void requestComment(OnResultCallback<List<CommentBean>> resultCallback, RemoteVoice voice, XingVoiceUser bean,int commentType) {

    }

    private static class SingletonHolder {
        private static final MainLocalDS INSTANCE = new MainLocalDS();
    }
}
