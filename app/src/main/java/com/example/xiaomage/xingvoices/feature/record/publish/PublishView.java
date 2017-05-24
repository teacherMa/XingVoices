package com.example.xiaomage.xingvoices.feature.record.publish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnDialogButtonClickListener;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;
import com.example.xiaomage.xingvoices.model.bean.upload.UploadResp;
import com.example.xiaomage.xingvoices.utils.AlertUtil;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.io.File;

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
    @BindView(R.id.iv_voice_bg)
    ImageView mIvVoiceBg;

    private LocalVoice mLocalVoice;
    private String mOriginPic = null;
    private String mCropPic = null;
    private String mTitle = null;
    private UploadResp mUploadResp;

    public void setOriginPic(String originPic) {
        mOriginPic = originPic;
    }

    public void setCropPic(String cropPic) {
        mCropPic = cropPic;
    }

    public void setLocalVoice(LocalVoice localVoice) {
        mLocalVoice = localVoice;
    }

    public PublishView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mEtVoiceName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitle = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        ((PublishActivity) getContext()).onBackPressed();
    }

    @OnClick(R.id.iv_record_confirm_upload)
    public void onMIvRecordConfirmUploadClicked() {
        if(null == mTitle){
            AlertUtil.showAlert(getContext(),BaseUtil.getString(R.string.record_no_title),
                    BaseUtil.getString(R.string.record_need_title));
            return;
        }
        if(null == mCropPic || null == mOriginPic){
            AlertUtil.showAlert(getContext(),BaseUtil.getString(R.string.record_no_pic),
                    BaseUtil.getString(R.string.record_need_pic));
            return;
        }
        getPresenter().upload(mLocalVoice.getPath(),mOriginPic,mCropPic);
        BaseUtil.showToast(BaseUtil.getString(R.string.record_is_upload));
    }

    @OnClick(R.id.iv_record_audition)
    public void onMIvRecordAuditionClicked() {
        getPresenter().audition(mLocalVoice.getPath());
    }

    @OnClick(R.id.iv_add_pic)
    public void onMIvAddPicClicked() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        ((Activity) getContext()).startActivityForResult(intent, Constants.PicCode.GALLERY);
    }

    public void loadCropPicture() {
        BaseUtil.load(new File(mCropPic)).into(mIvVoiceBg);
        mIvAddPic.setVisibility(INVISIBLE);
    }

    @Override
    public void uploadSuccess(UploadResp resp) {
        mUploadResp = resp;
        getPresenter().publishRecord(mTitle,mUploadResp.getData().getAFile(),mLocalVoice.getLength(),
                mUploadResp.getData().getCFile(),mUploadResp.getData().getBFile());
    }

    @Override
    public void publishSuccess(boolean success) {
        AlertUtil.showAlert(getContext(), BaseUtil.getString(R.string.record_publish),
                BaseUtil.getString(R.string.record_publish_success), new OnDialogButtonClickListener() {
                    @Override
                    public void onDialogButtonClick() {
                        ((PublishActivity)getContext()).onBackPressed();
                    }
                });

    }
}
