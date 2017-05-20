package com.example.xiaomage.xingvoices.utils;

import android.os.Environment;

import com.example.xiaomage.xingvoices.App;

import java.io.File;

/**
 * Created by xiaomage on 2017/5/19.
 */

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static final String FILE_EXTENSION = ".png";

    public static final String FLODER_SEPARATOR = File.separator;

    public static String PATH;
    private static String USER_FILE;
    private static String USER_HEAD_FILE;

    static {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            PATH = App.getAppContext().getExternalFilesDir(null).getPath();
        }else {
            PATH = App.getAppContext().getFilesDir().getPath();
        }
    }


    public static void setUSER_FILE(String userId) {
        USER_FILE = PATH.concat("/"+userId);
    }

    public static String getUSER_FILE() {
        return USER_FILE;
    }

    public static String getUserHeadFile() {
        return USER_HEAD_FILE;
    }

    public static void setUserHeadFile(String userHeadFile) {
        USER_HEAD_FILE = userHeadFile;
    }
}
