package com.example.xiaomage.xingvoices.model.record.local;

import android.media.MediaPlayer;

import com.czt.mp3recorder.MP3Recorder;
import com.example.xiaomage.xingvoices.api.OnResultCallback;
import com.example.xiaomage.xingvoices.event.UpdateAuditionProgressEvent;
import com.example.xiaomage.xingvoices.model.bean.uploadResp.UploadResp;
import com.example.xiaomage.xingvoices.model.record.RecordDataSource;
import com.example.xiaomage.xingvoices.utils.Constants;
import com.example.xiaomage.xingvoices.utils.FileUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class RecordLocalDS implements RecordDataSource {
    private MP3Recorder mMP3Recorder;
    private MediaPlayer mMediaPlayer;
    private String mRecordPath;
    private String mAuditionPath;
    private boolean mNeedUpdate;

    private RecordLocalDS() {
    }

    private Thread mUpdateThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                if(mNeedUpdate){
                    EventBus.getDefault().post(
                            new UpdateAuditionProgressEvent(mMediaPlayer.getCurrentPosition())
                    );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    });

    public static RecordLocalDS getInstance() {
        return RecordLocalDS.SingletonHolder.INSTANCE;
    }

    @Override
    public void recordAudio(OnResultCallback<String> resultCallback, boolean toStart) {

        if(toStart) {
            String id = String.valueOf(new Date().getTime());
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
            resultCallback.onSuccess(mRecordPath,Constants.ResultCode.LOCAL);
            mMP3Recorder = null;
            mRecordPath = null;
        }

    }

    @Override
    public void deleteAudio(OnResultCallback<Boolean> resultCallback, String file) {
        File f = new File(file);
        if(!f.exists()){
            resultCallback.onFail(Constants.ResponseError.DATA_EMPTY);
            return;
        }
        if(f.delete()){
            resultCallback.onSuccess(true,Constants.ResultCode.LOCAL);
            return;
        }
        resultCallback.onFail(Constants.ResponseError.UNKNOWN);
    }

    @Override
    public void audition(final OnResultCallback<Integer> resultCallback, String path) {
        if(!mUpdateThread.isAlive()){
            mUpdateThread.start();
        }

        if(null == mMediaPlayer){
            mMediaPlayer = new MediaPlayer();
        }

        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
            mNeedUpdate = false;
            return;
        }

        if(null != mAuditionPath){
            mMediaPlayer.start();
            mNeedUpdate = true;
            return;
        }

        try {
            mMediaPlayer.setDataSource(path);
            mAuditionPath = path;
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    resultCallback.onSuccess(mp.getCurrentPosition(),Constants.ResultCode.LOCAL);
                    mAuditionPath = null;
                    mMediaPlayer.reset();
                    mNeedUpdate = false;
                }
            });
            mMediaPlayer.prepare();

            mMediaPlayer.start();
            mNeedUpdate = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void upload(OnResultCallback<UploadResp> resultCallback, String voicePath, String originPicPath, String cropPicPath) {

    }

    @Override
    public void publish(OnResultCallback<String> resultCallback, String title, String recordPath, int length, String cropPic, String originPic) {

    }

    private static class SingletonHolder {
        private static final RecordLocalDS INSTANCE = new RecordLocalDS();
    }
}
