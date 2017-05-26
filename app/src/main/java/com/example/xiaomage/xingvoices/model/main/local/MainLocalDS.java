package com.example.xiaomage.xingvoices.model.main.local;

import android.media.MediaPlayer;
import android.os.AsyncTask;

import com.czt.mp3recorder.MP3Recorder;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.model.UserManager;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.User;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.model.bean.followResp.FollowResp;
import com.example.xiaomage.xingvoices.model.bean.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.model.bean.publishCommentResp.CommentResp;
import com.example.xiaomage.xingvoices.model.main.MainDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import okhttp3.ResponseBody;


public class MainLocalDS implements MainDataSource {
    private String mCurPlayVoiceId;
    private MediaPlayer mMediaPlayer;
    private OnResultCallback<Boolean> mCurCallback;
    private MP3Recorder mMP3Recorder;
    private String mRecordPath;
    private String mRecordId;

    private MainLocalDS() {
    }

    public static MainLocalDS getInstance() {
        return MainLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void login(OnResultCallback<XingVoiceUserResp> resultCallback, WxUserInfo info, XingVoiceUserResp xingVoiceUserResp) {

        User user = new User();
        user.setAvatar(info.getHeadimgurl());
        user.setId(xingVoiceUserResp.getUser().getUid());
        user.setName(xingVoiceUserResp.getUser().getNickname());

        UserManager.getInstance().setCurrentUser(user);

        Realm realm = Realm.getDefaultInstance();

        if(realm.where(XingVoiceUser.class).findAll().size()!=0){
            resultCallback.onSuccess(xingVoiceUserResp, Constants.ResultCode.LOCAL);
            return;
        }

        realm.beginTransaction();

        final XingVoiceUser xingVoiceUser = xingVoiceUserResp.getUser();
        xingVoiceUser.setHeadpic(info.getHeadimgurl());
        realm.copyToRealm(xingVoiceUser);

        realm.commitTransaction();

        resultCallback.onSuccess(xingVoiceUserResp,Constants.ResultCode.LOCAL);

    }

    @Override
    public void getUserInfo(OnResultCallback<BasicUserInfo> resultCallback, String uid, String cid) {

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
    public void requestPopularVoicesList(OnResultCallback<List<RemoteVoice>> resultCallback, String uid,
                                         int is_u, String cid, int page, int num) {

    }


    @Override
    public void requestComment(OnResultCallback<List<CommentBean>> resultCallback, RemoteVoice voice,
                               XingVoiceUser bean,int commentType) {

    }

    @Override
    public void downloadVoice(OnResultCallback<ResponseBody> resultCallback, ResponseBody responseBody,
                              String vUrl,String vId) {
        String path = FileUtil.VOICE_FILE.concat("/").concat(vId).concat(".mp3");
        SaveVoiceTask saveVoiceTask = new SaveVoiceTask(responseBody,path,resultCallback,vId);
        saveVoiceTask.execute();

    }

    private class SaveVoiceTask extends AsyncTask<Void,Void,Boolean>{

        private ResponseBody mResponseBody;
        private String mPath;
        private String mVId;
        private OnResultCallback<ResponseBody> mResponseBodyOnResultCallback;

        public SaveVoiceTask(ResponseBody responseBody, String path, OnResultCallback<ResponseBody>
                responseBodyOnResultCallback,String vId) {
            mResponseBody = responseBody;
            mPath = path;
            mResponseBodyOnResultCallback = responseBodyOnResultCallback;
            mVId = vId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            File file = new File(mPath);
            if(!file.exists()){
                file.getParentFile().mkdir();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    mResponseBodyOnResultCallback.onFail(Constants.ResponseError.UNKNOWN);
                }
            }

            byte[] readFile = new byte[4096];
            InputStream inputStream = mResponseBody.byteStream();
            try {
                OutputStream outputStream = new FileOutputStream(file);
                while (true){
                    int read = inputStream.read(readFile);
                    if(read == -1){
                        break;
                    }
                    outputStream.write(readFile,0,read);
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

            if(null == mResponseBodyOnResultCallback){
                return;
            }

            if(aBoolean){

                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                LocalVoice localVoice = new LocalVoice(mPath,mVId);
                realm.copyToRealm(localVoice);

                realm.commitTransaction();

                mResponseBodyOnResultCallback.onSuccess(mResponseBody,Constants.ResultCode.LOCAL);
                return;
            }
            mResponseBodyOnResultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
        }
    }

    @Override
    public void playVoice(final OnResultCallback<Boolean> resultCallback, String vId) {
        //初始化MediaPlayer
        if(null == mMediaPlayer){
            mMediaPlayer = new MediaPlayer();
        }
        //如果当前有语音正在播放,先停止播放
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            //reset之后，会移除一系列的listener
            mMediaPlayer.reset();
            //判断:仅暂停当前播放，还是需要播放新语言?
            mCurCallback.onSuccess(true,Constants.ResultCode.LOCAL);

            if(mCurPlayVoiceId.equals(vId)){
                return;
            }
        }
        //播放语音
        try {
            mCurPlayVoiceId = vId;
            mCurCallback = resultCallback;

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mCurPlayVoiceId = null;
                    mCurCallback = null;
                    resultCallback.onSuccess(true,Constants.ResultCode.LOCAL);
                    mp.reset();
                }
            });
            mMediaPlayer.setDataSource(FileUtil.getVoicePath(vId));
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void likeIt(OnResultCallback<String> resultCallback, String cId) {

    }

    @Override
    public void playVoiceCom(OnResultCallback<Boolean> resultCallback, CommentBean commentBean) {

    }

    @Override
    public void recordAudio(OnResultCallback<String> resultCallback, boolean toStart) {
        if(toStart) {
            String id = String.valueOf(new Date().getTime());
            mRecordId = id;
            Object o = new Object() ;
            String path = FileUtil.RECORD_FILE.concat("/").concat(id).concat(".mp3");
            mRecordPath = path;
            File file = new File(path);
            if(!file.exists()){
                file.getParentFile().mkdir();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
                }
            }
            mMP3Recorder = new MP3Recorder(file);
            try {
                mMP3Recorder.start();
            } catch (IOException e) {
                e.printStackTrace();
                resultCallback.onFail(Constants.ResponseError.UNKNOWN);
                return;
            }
            resultCallback.onSuccess(mRecordPath,Constants.ResultCode.LOCAL);
            return;
        }
        if(null == mMP3Recorder){
            return;
        }
        if(mMP3Recorder.isRecording()){
            mMP3Recorder.stop();

            LocalVoice localVoice = new LocalVoice();
            localVoice.setId(mRecordId);
            localVoice.setPath(mRecordPath);

            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealm(localVoice);
            realm.commitTransaction();

            resultCallback.onSuccess(mRecordId,Constants.ResultCode.LOCAL);

            mMP3Recorder = null;
            mRecordPath = null;
            mRecordId = null;
        }
    }

    @Override
    public void publishTextCom(OnResultCallback<CommentResp> resultCallback, String vId, String content) {

    }

    @Override
    public void publishVoiceCom(OnResultCallback<CommentResp> resultCallback, String vId, String cId, int cLength) {

    }

    @Override
    public void changeFollowState(OnResultCallback<FollowResp> resultCallback, String cid, int state) {

    }

    @Override
    public void requestMyVoiceComments(OnResultCallback<List<MyVoiceCommentResp>> resultCallback, int num) {

    }


    private static class SingletonHolder {
        private static final MainLocalDS INSTANCE = new MainLocalDS();
    }
}
