package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VoiceCommentActivity extends BaseFragment<VoiceCommentPresenter> {

    @BindView(R.id.voice_com_view)
    VoiceCommentView mVoiceComView;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected VoiceCommentPresenter createPresenter() {
        return new VoiceCommentPresenter(
                Injection.provideMainRepository(),
                mVoiceComView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_voice_com_frag;
    }

}
