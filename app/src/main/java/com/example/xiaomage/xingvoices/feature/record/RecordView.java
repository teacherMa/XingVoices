package com.example.xiaomage.xingvoices.feature.record;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordView extends BaseView<RecordContract.Presenter> implements RecordContract.View {

    private static final String TIMER_FORMAT = "mm:ss";

    @BindView(R.id.iv_start_record)
    ImageView mIvStartRecord;
    @BindView(R.id.tv_record_time_hint)
    TextView mTvRecordTimeHint;
    @BindView(R.id.tv_record_again)
    TextView mTvRecordAgain;
    @BindView(R.id.iv_record_again)
    ImageView mIvRecordAgain;
    @BindView(R.id.tv_record_save)
    TextView mTvRecordSave;
    @BindView(R.id.iv_record_save)
    ImageView mIvRecordSave;
    @BindView(R.id.iv_cancel_record)
    ImageView mIvCancelRecord;
    @BindView(R.id.voice_timer)
    Chronometer mVoiceTimer;
    @BindView(R.id.ll_record_time)
    LinearLayout mLlRecordTime;
    @BindView(R.id.iv_audition)
    ImageView mIvAudition;
    @BindView(R.id.rl_show_time)
    RelativeLayout mRlShowTime;

    private boolean mIsRecording = false;

    public RecordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.record_view;
    }

    @OnClick({R.id.iv_start_record, R.id.tv_record_time_hint, R.id.tv_record_again,
            R.id.iv_record_again, R.id.tv_record_save, R.id.iv_record_save, R.id.iv_cancel_record,
            R.id.ll_record_time, R.id.iv_audition})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_start_record:
                if(!mIsRecording) {
                    mVoiceTimer.setBase(SystemClock.elapsedRealtime());
                    mVoiceTimer.start();
                }
                break;
            case R.id.tv_record_time_hint:
                break;
            case R.id.tv_record_again:
                break;
            case R.id.iv_record_again:
                break;
            case R.id.tv_record_save:
                break;
            case R.id.iv_record_save:
                break;
            case R.id.iv_cancel_record:
                releaseAll();
                NavUtils.navigateUpFromSameTask((RecordActivity)getContext());
                break;
            case R.id.ll_record_time:
                break;
            case R.id.iv_audition:
                break;
        }
    }

    private void releaseAll(){

    }
}
