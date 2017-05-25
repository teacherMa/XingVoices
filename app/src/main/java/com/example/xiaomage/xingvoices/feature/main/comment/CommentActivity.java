package com.example.xiaomage.xingvoices.feature.main.comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends BaseActivity<CommentPresenter> {
    private static final String ARG_REMOTE_VOICE = "remote voice";

    @BindView(R.id.com_view)
    CommentView mComView;

    private RemoteVoice mRemoteVoice;

    public static Intent getNewIntent(RemoteVoice remoteVoice, Context context){
        Intent intent = new Intent(context,CommentActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_REMOTE_VOICE, remoteVoice);

        intent.putExtras(bundle);

        return intent;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected CommentPresenter createPresenter() {
        mRemoteVoice = (RemoteVoice)getIntent().getSerializableExtra(ARG_REMOTE_VOICE);
        mComView.setRemoteVoice(mRemoteVoice);
        return new CommentPresenter(
                Injection.provideMainRepository(),
                mComView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.com_act;
    }

}
