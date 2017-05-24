package com.example.xiaomage.xingvoices.utils;

import android.os.Environment;

import com.example.xiaomage.xingvoices.App;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by xiaomage on 2017/5/19.
 */

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static final String FILE_EXTENSION = ".png";

    public static final String FLODER_SEPARATOR = File.separator;

    public static String PATH;
    public static String VOICE_FILE;
    public static String RECORD_FILE;
    public static String PATH_TEMP;


    static {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            PATH = App.getAppContext().getExternalFilesDir(null).getPath();
        }else {
            PATH = App.getAppContext().getFilesDir().getPath();
        }
        VOICE_FILE = PATH.concat("/voice");
        RECORD_FILE = PATH.concat("/record");
        PATH_TEMP = PATH.concat("/temp");
    }

    public static String getVoicePath(String vId){
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<LocalVoice> query = realm.where(LocalVoice.class);
        RealmResults<LocalVoice> results = query.equalTo("mId",vId).findAll();
        if(null == results || 0 == results.size()){
            return null;
        }
        return results.get(0).getPath();
    }

}
