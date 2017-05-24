package com.example.xiaomage.xingvoices.model.bean.LocalVoice;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by xiaomage on 2017/5/8.
 *
 */

public class LocalVoice extends RealmObject implements Serializable {

    private String mPath;

    private String mId;

    private int mLength;

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public LocalVoice(String path, String id) {
        mPath = path;
        mId = id;
    }

    public LocalVoice(){}

    public String getPath() {
        return mPath;
    }

    public String getId() {
        return mId;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public void setId(String id) {
        mId = id;
    }
}
