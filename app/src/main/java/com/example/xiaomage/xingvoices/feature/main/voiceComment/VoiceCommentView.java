package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

import java.util.List;

import butterknife.BindView;

public class VoiceCommentView extends BaseView<VoiceCommentContract.Presenter> implements VoiceCommentContract.View {

    @BindView(R.id.rv_voice_com)
    RecyclerView mRvVoiceCom;

    private VoiceCommentAdapter mAdapter;
    private RemoteVoice mRemoteVoice;

    public VoiceCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new VoiceCommentAdapter();
        mRvVoiceCom.setItemAnimator(new DefaultItemAnimator());
        mRvVoiceCom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvVoiceCom.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_voice_com_view;
    }

    @Override
    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    @Override
    public void updateData(List<CommentBean> been) {
        if(null == mRvVoiceCom.getAdapter()){
            VoiceCommentAdapter adapter = new VoiceCommentAdapter();
            mRvVoiceCom.setAdapter(adapter);
        }
        ((VoiceCommentAdapter)mRvVoiceCom.getAdapter()).refreshData(been);
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }
}
