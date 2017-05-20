package com.example.xiaomage.xingvoices.feature.record;

import android.content.Context;
import android.content.Intent;
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
import com.example.xiaomage.xingvoices.feature.record.publish.PublishActivity;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.IS_AUDITION;
import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.IS_RECORDING;
import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.PREPARE_AUDITION;
import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.PREPARE_RECORD;

public class RecordView extends BaseView<RecordContract.Presenter> implements RecordContract.View {

    private static final int REQUEST_UPLOAD = 0;

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
    @BindView(R.id.audition_progress_bar)
    View mAuditionProgressBar;
    @BindView(R.id.ll_audition_time)
    LinearLayout mLlAuditionTime;

    private RecordState mRecordState;

    public RecordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mRecordState = new RecordState(mTvRecordAgain, mTvRecordSave, mTvRecordTimeHint,
                mIvRecordAgain, mIvRecordSave, mIvStartRecord, mIvAudition, mAuditionProgressBar,
                mLlAuditionTime, mVoiceTimer);
        mRecordState.updateState();
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
                if (mRecordState.getCurrentState() == PREPARE_RECORD) {
                    mRecordState.setCurrentState(IS_RECORDING);
                    mRecordState.updateState();
                    return;
                }
                if (mRecordState.getCurrentState() == IS_RECORDING) {
                    mRecordState.setCurrentState(PREPARE_AUDITION);
                    mRecordState.updateState();
                    return;
                }
                if (mRecordState.getCurrentState() == IS_AUDITION) {
                    mRecordState.setCurrentState(IS_RECORDING);
                    mRecordState.updateState();
                    return;
                }
                break;
            case R.id.iv_record_again:
                mRecordState.setCurrentState(PREPARE_RECORD);
                mRecordState.updateState();
                break;
            case R.id.iv_record_save:
                LocalVoice localVoice = new LocalVoice();
                localVoice.setNum(12345);
                if (mRecordState.getCurrentState() == IS_AUDITION ||
                        mRecordState.getCurrentState() == PREPARE_AUDITION) {
                    Intent intent = PublishActivity.getNewIntent(getContext(),localVoice);
                    ((RecordActivity)getContext()).startActivityForResult(intent,REQUEST_UPLOAD);
                }
                break;
            case R.id.iv_cancel_record:
                releaseAll();
                NavUtils.navigateUpFromSameTask((RecordActivity) getContext());
                break;
            case R.id.iv_audition:
                if (mRecordState.getCurrentState() == PREPARE_AUDITION) {
                    mRecordState.setCurrentState(IS_AUDITION);
                    mRecordState.updateState();
                    return;
                }
                if (mRecordState.getCurrentState() == IS_AUDITION) {
                    mRecordState.setCurrentState(PREPARE_AUDITION);
                    mRecordState.updateState();
                    return;
                }
                break;
            default:
                break;
        }
    }

    private void releaseAll() {

    }

}
