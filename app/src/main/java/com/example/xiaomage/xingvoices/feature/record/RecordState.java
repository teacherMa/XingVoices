package com.example.xiaomage.xingvoices.feature.record;

import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by xiaomage on 2017/5/8.
 *
 */

public class RecordState {

    private int mCurrentState;

    private TextView mTvAgain;
    private TextView mTvSave;
    private TextView mTvHint;
    private ImageView mIvAgain;
    private ImageView mIvSave;
    private ImageView mIvHint;
    private ImageView mIvAudition;
    private View mAudiProBar;
    private LinearLayout mAudiTime;

    private Chronometer mVoiceTimer;

    public RecordState( TextView tvAgain, TextView tvSave, TextView tvHint, ImageView ivAgain,
                        ImageView ivSave, ImageView ivHint, ImageView ivAudition, View audiProBar,
                        LinearLayout audiTime,Chronometer voiceTimer) {
        mTvAgain = tvAgain;
        mTvSave = tvSave;
        mTvHint = tvHint;
        mIvAgain = ivAgain;
        mIvSave = ivSave;
        mIvHint = ivHint;
        mIvAudition = ivAudition;
        mAudiProBar = audiProBar;
        mAudiTime = audiTime;
        mVoiceTimer = voiceTimer;

        mVoiceTimer.setBase(SystemClock.elapsedRealtime());
    }

    public void setCurrentState(int currentState) {
        mCurrentState = currentState;
    }

    public int getCurrentState() {

        return mCurrentState;
    }

    public void updateState(){

        switch (mCurrentState){
            case Constants.RecordState.PREPARE_RECORD:
                mTvAgain.setVisibility(GONE);
                mTvSave.setVisibility(GONE);
                mTvHint.setText(BaseUtil.getString(R.string.record_time_hint));
                mIvAgain.setVisibility(GONE);
                mIvAudition.setVisibility(GONE);

                mIvHint.setVisibility(VISIBLE);
                mIvHint.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_start_record));

                mIvSave.setVisibility(GONE);
                mAudiProBar.setVisibility(GONE);
                mAudiTime.setVisibility(GONE);
                mVoiceTimer.setBase(SystemClock.elapsedRealtime());
                break;
            case Constants.RecordState.IS_RECORDING:

                mTvAgain.setVisibility(GONE);
                mTvSave.setVisibility(GONE);
                mTvHint.setText(BaseUtil.getString(R.string.record_time_hint));
                mIvAgain.setVisibility(GONE);
                mIvAudition.setVisibility(GONE);

                mIvHint.setVisibility(VISIBLE);
                mIvHint.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_start_record));

                mIvSave.setVisibility(GONE);
                mAudiProBar.setVisibility(GONE);
                mAudiTime.setVisibility(GONE);
                mVoiceTimer.start();
                break;
            case Constants.RecordState.PREPARE_AUDITION:
                mTvAgain.setVisibility(VISIBLE);
                mTvSave.setVisibility(VISIBLE);
                mTvHint.setText(BaseUtil.getString(R.string.record_stop));
                mIvAgain.setVisibility(VISIBLE);

                mIvAudition.setVisibility(VISIBLE);
                mIvAudition.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_audition));

                mIvHint.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_pause));
                mIvSave.setVisibility(VISIBLE);
                mAudiProBar.setVisibility(GONE);
                mAudiTime.setVisibility(GONE);
                mVoiceTimer.stop();
                break;
            case Constants.RecordState.IS_AUDITION:
                mTvAgain.setVisibility(VISIBLE);
                mTvSave.setVisibility(VISIBLE);
                mTvHint.setText(BaseUtil.getString(R.string.record_continue));
                mIvAgain.setVisibility(VISIBLE);

                mIvAudition.setVisibility(VISIBLE);
                mIvAudition.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_pause_audition));

                mIvHint.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_pause));
                mIvSave.setVisibility(VISIBLE);
                mAudiProBar.setVisibility(VISIBLE);
                mAudiTime.setVisibility(VISIBLE);
                mVoiceTimer.stop();
                break;
            default:
                break;
        }
    }
}
