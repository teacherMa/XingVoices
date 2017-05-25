package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.main.OnBottomCommentItemClickListener;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.utils.AlertUtil;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by xiaomage on 2017/5/23.
 */

public class BottomCommentView extends PopupWindow {

    private Context mContext;

    private ImageView mIvToVoiceCom;
    private Button mIvSendCom;
    private EditText mEtComContent;
    private RatioLayout mRatioLayout;
    private TextView mHintText;
    private ImageView mIvRecord;
    private ImageView mIvAudition;
    private Chronometer mChronometer;
    private RelativeLayout mRelativeLayoutText;
    private LinearLayout mLinearLayoutSend;
    private Button mButtonCancel;
    private Button mButtonSend;

    private OnBottomCommentItemClickListener mItemClickListener;

    private boolean mIsRecord = false;
    private boolean mIsAudition = false;
    private String mTextCom;
    private int mCurRecordLength;

    public BottomCommentView(Context context) {
        super(context);
        mContext = context;

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.main_bottom_com, null);

        setContentView(contentView);

        setChildrenClickListener(contentView);

        setFocusable(true);
        setTouchable(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.STOP_RECORD, null);
                WindowManager.LayoutParams layoutParams = ((MainActivity) mContext).getWindow().getAttributes();
                layoutParams.alpha = 1.0f;
                ((MainActivity) mContext).getWindow().setAttributes(layoutParams);
            }
        });

        this.setAnimationStyle(R.style.BottomMenuAnim);
        this.setBackgroundDrawable(new BitmapDrawable());

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        WindowManager.LayoutParams layoutParams = ((MainActivity) mContext).getWindow().getAttributes();
        layoutParams.alpha = 0.5f;
        ((MainActivity) mContext).getWindow().setAttributes(layoutParams);
    }

    private void setChildrenClickListener(View contentView) {

        mRelativeLayoutText = (RelativeLayout) contentView.findViewById(R.id.rl_text);

        mIvToVoiceCom = (ImageView) contentView.findViewById(R.id.iv_to_voice_com);
        mIvToVoiceCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRatioLayout.setVisibility(VISIBLE);
                mRelativeLayoutText.setVisibility(GONE);
                mEtComContent.clearFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager == null) {
                    return;
                }
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        mIvAudition = (ImageView) contentView.findViewById(R.id.iv_to_audition);
        mIvAudition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsAudition){
                    mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.STOP_AUDITION, null);
                    mIvAudition.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_audition));
                    mIsAudition = false;
                    return;
                }
                mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.TO_AUDITION, null);
                mIvAudition.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_pause_audition));
                mIsAudition = true;
            }
        });

        mIvSendCom = (Button) contentView.findViewById(R.id.iv_send_com);
        mIvSendCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mTextCom){
                    AlertUtil.showAlert(mContext,BaseUtil.getString(R.string.record_com_no_content),
                            BaseUtil.getString(R.string.record_com_need_content));
                    return;
                }
                mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.SEND_TEXT_COM,mTextCom);
                dismiss();
            }
        });

        mEtComContent = (EditText) contentView.findViewById(R.id.et_voice_com_content);
        mEtComContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextCom = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mRatioLayout = (RatioLayout) contentView.findViewById(R.id.rl_voice_com);

        mChronometer = (Chronometer) contentView.findViewById(R.id.voice_timer);

        mIvRecord = (ImageView) contentView.findViewById(R.id.iv_to_com);
        mIvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsRecord) {
                    mIsRecord = true;
                    mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.START_RECORD, null);
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.start();
                    mIvRecord.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_pause));
                    return;
                }
                mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.STOP_RECORD, null);
                mIsRecord = false;

                if (isTimeEnough()) {
                    mIvAudition.setVisibility(VISIBLE);
                    mIvRecord.setVisibility(GONE);
                    mLinearLayoutSend.setVisibility(VISIBLE);
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.stop();
                    mChronometer.setVisibility(GONE);
                    mHintText.setVisibility(GONE);
                    return;
                }
                mIvRecord.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_record_start_record));
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.stop();
                AlertUtil.showAlert(mContext,BaseUtil.getString(R.string.record_time_too_short),
                        BaseUtil.getString(R.string.record_less_than_five));
            }
        });

        mHintText = (TextView)contentView.findViewById(R.id.tv_voice_com_hint);

        mLinearLayoutSend = (LinearLayout) contentView.findViewById(R.id.ll_send_button);

        mButtonCancel = (Button) contentView.findViewById(R.id.cancel_button);
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mButtonSend = (Button) contentView.findViewById(R.id.send_button);
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onBottomCommentItemClick(Constants.BottomComItem.SEND_VOICE_COM, Integer.toString(mCurRecordLength));
                dismiss();
            }
        });
    }

    public void setItemClickListener(OnBottomCommentItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    private boolean isTimeEnough() {
        int temp0 = Integer.parseInt(mChronometer.getText().toString().split(":")[0]);
        int temp1 = Integer.parseInt(mChronometer.getText().toString().split(":")[1]);
        mCurRecordLength = temp0 * 60 + temp1;
        return mCurRecordLength >= 5;
    }

}
