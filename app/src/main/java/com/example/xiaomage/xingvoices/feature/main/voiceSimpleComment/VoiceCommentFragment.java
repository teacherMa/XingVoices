package com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class VoiceCommentFragment extends BaseFragment<VoiceCommentPresenter> {
    @BindView(R.id.voice_com_view)
    VoiceCommentView mVoiceComView;

    private RemoteVoice mRemoteVoice;
    private int mOutVHPosition;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected VoiceCommentPresenter createPresenter() {
        mVoiceComView.setRemoteVoice(mRemoteVoice);
        return new VoiceCommentPresenter(
                Injection.provideMainRepository(),
                mVoiceComView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_simple_voice_com_frag;
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    public void setOutVHPosition(int outVHPosition) {
        mOutVHPosition = outVHPosition;
    }
}
