package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextCommentFragment extends BaseFragment<TextCommentPresenter> {

    @BindView(R.id.text_com_view)
    TextCommentView mTextComView;

    private RemoteVoice mRemoteVoice;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected TextCommentPresenter createPresenter() {

        mTextComView.setRemoteVoice(mRemoteVoice);

        return new TextCommentPresenter(
                Injection.provideMainRepository(),
                mTextComView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_text_com_frag;
    }


    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }
}
