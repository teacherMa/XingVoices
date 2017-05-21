package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VoiceCommentFragment extends BaseFragment<VoiceCommentPresenter> {

    @BindView(R.id.voice_com_view)
    VoiceCommentView mVoiceComView;

    private int mModel;
    private RemoteVoice mRemoteVoice;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected VoiceCommentPresenter createPresenter() {

        mVoiceComView.setRemoteVoice(mRemoteVoice);
        mVoiceComView.setModel(mModel);

        return new VoiceCommentPresenter(
                Injection.provideMainRepository(),
                mVoiceComView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_voice_com_frag;
    }

    public int getModel() {
        return mModel;
    }

    public void setModel(int model) {
        mModel = model;
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

}
