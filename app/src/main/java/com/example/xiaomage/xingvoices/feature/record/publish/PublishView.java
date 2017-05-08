package com.example.xiaomage.xingvoices.feature.record.publish;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice;

import butterknife.BindView;
import butterknife.OnClick;

public class PublishView extends BaseView<PublishContract.Presenter> implements PublishContract.View {

    @BindView(R.id.iv_back_record)
    ImageView mIvBackRecord;
    @BindView(R.id.iv_cancel_upload)
    ImageView mIvCancelUpload;
    @BindView(R.id.iv_record_confirm_upload)
    ImageView mIvRecordConfirmUpload;
    @BindView(R.id.iv_record_audition)
    ImageView mIvRecordAudition;
    @BindView(R.id.et_voice_name)
    EditText mEtVoiceName;
    @BindView(R.id.iv_add_pic)
    ImageView mIvAddPic;
    @BindView(R.id.tv_publish_voice_length)
    TextView mTvPublishVoiceLength;

    private LocalVoice mLocalVoice;

    public void setLocalVoice(LocalVoice localVoice) {
        mLocalVoice = localVoice;
    }

    public PublishView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.record_publish_view;
    }

    @OnClick(R.id.iv_back_record)
    public void onMIvBackRecordClicked() {
        ((PublishActivity) getContext()).finish();
    }

    @OnClick(R.id.iv_cancel_upload)
    public void onMIvCancelUploadClicked() {
        NavUtils.navigateUpFromSameTask((PublishActivity) getContext());
    }

    @OnClick(R.id.iv_record_confirm_upload)
    public void onMIvRecordConfirmUploadClicked() {
    }

    @OnClick(R.id.iv_record_audition)
    public void onMIvRecordAuditionClicked() {
    }

    @OnClick(R.id.iv_add_pic)
    public void onMIvAddPicClicked() {
    }

}
