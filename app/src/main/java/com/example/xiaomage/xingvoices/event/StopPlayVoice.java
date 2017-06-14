package com.example.xiaomage.xingvoices.event;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class StopPlayVoice extends EmptyEvent {
    boolean mToStop;

    public void setToStop(boolean toStop) {
        mToStop = toStop;
    }

    public boolean isToStop() {

        return mToStop;
    }

    public StopPlayVoice(boolean toStop) {

        mToStop = toStop;
    }
}
