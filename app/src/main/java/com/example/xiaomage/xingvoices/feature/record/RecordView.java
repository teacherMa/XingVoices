package com.example.xiaomage.xingvoices.feature.record;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.UpdateAuditionProgressEvent;
import com.example.xiaomage.xingvoices.feature.record.publish.PublishActivity;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;
import com.example.xiaomage.xingvoices.utils.AlertUtil;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.IS_AUDITION;
import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.IS_RECORDING;
import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.PREPARE_AUDITION;
import static com.example.xiaomage.xingvoices.utils.Constants.RecordState.PREPARE_RECORD;

public class RecordView extends BaseBusView<RecordContract.Presenter> implements RecordContract.View {

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
    @BindView(R.id.tv_audition_length_min)
    TextView mTvAuditionLengthMin;
    @BindView(R.id.tv_audition_length_sec)
    TextView mTvAuditionLengthSec;

    private RecordState mRecordState;
    private String mCurRecordPath;
    private int mCurRecordLength;

    public RecordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {
        if (event == null) {
            return;
        }
        if (event instanceof UpdateAuditionProgressEvent) {
            UpdateAuditionProgressEvent auditionProgressEvent = (UpdateAuditionProgressEvent) event;
            updateProgress(auditionProgressEvent.getCurPosition());
        }
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mRecordState = new RecordState(mTvRecordAgain, mTvRecordSave, mTvRecordTimeHint,
                mIvRecordAgain, mIvRecordSave, mIvStartRecord, mIvAudition, mAuditionProgressBar,
                mLlAuditionTime, mVoiceTimer);
        mRecordState.init();
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
                    getPresenter().recordAudio(true);
                    return;
                }
                if (mRecordState.getCurrentState() == IS_RECORDING) {
                    mRecordState.setCurrentState(PREPARE_AUDITION);
                    getPresenter().recordAudio(false);

                    int temp0 = Integer.parseInt(mVoiceTimer.getText().toString().split(":")[0]);
                    int temp1 = Integer.parseInt(mVoiceTimer.getText().toString().split(":")[1]);
                    mCurRecordLength = temp0 * 60 + temp1;
                    if (mCurRecordLength < 10) {
                        mRecordState.setCurrentState(PREPARE_RECORD);
                        getPresenter().deleteAudio(mCurRecordPath);
                        AlertUtil.showAlert(getContext(),
                                BaseUtil.getString(R.string.record_time_too_short),
                                BaseUtil.getString(R.string.record_less_than_ten));
                    }
                    return;
                }
                if (mRecordState.getCurrentState() == IS_AUDITION) {
                    mRecordState.setCurrentState(IS_RECORDING);
                    return;
                }
                break;
            case R.id.iv_record_again:
                mRecordState.setCurrentState(PREPARE_RECORD);
                getPresenter().deleteAudio(mCurRecordPath);
                break;
            case R.id.iv_record_save:
                LocalVoice localVoice = new LocalVoice();
                localVoice.setPath(mCurRecordPath);
                localVoice.setLength(mCurRecordLength);
                if (mRecordState.getCurrentState() == IS_AUDITION ||
                        mRecordState.getCurrentState() == PREPARE_AUDITION) {
                    Intent intent = PublishActivity.getNewIntent(getContext(), localVoice);
                    ((RecordActivity) getContext()).startActivityForResult(intent, REQUEST_UPLOAD);
                }
                break;
            case R.id.iv_cancel_record:
                releaseAll();
                ((RecordActivity) getContext()).onBackPressed();
                break;
            case R.id.iv_audition:
                if (mRecordState.getCurrentState() == PREPARE_AUDITION) {
                    mRecordState.setCurrentState(IS_AUDITION);
                    getPresenter().audition(mCurRecordPath);
                    return;
                }
                if (mRecordState.getCurrentState() == IS_AUDITION) {
                    mRecordState.setCurrentState(PREPARE_AUDITION);
                    getPresenter().audition(mCurRecordPath);
                    return;
                }
                break;
            default:
                break;
        }
    }

    private void releaseAll() {

    }

    @Override
    public void recordSuccess(String path) {
        mCurRecordPath = path;
    }

    @Override
    public void deleteSuccess() {

    }

    @Override
    public void isAudition(Integer length) {
        if (mCurRecordLength * 1000 > length - 1000 || mCurRecordLength * 1000 < length + 1000) {
            mRecordState.setCurrentState(PREPARE_AUDITION);
        }
    }

    private void updateProgress(int curPosition) {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams layoutParams = mAuditionProgressBar.getLayoutParams();
        layoutParams.width = metrics.widthPixels * curPosition / (mCurRecordLength * 1000);

        int curSec = curPosition/1000 +1;
        mTvAuditionLengthSec.setText(String.valueOf(curSec%60));
        mTvAuditionLengthMin.setText(String.valueOf(curSec/60));

        mAuditionProgressBar.setLayoutParams(layoutParams);
    }
}
