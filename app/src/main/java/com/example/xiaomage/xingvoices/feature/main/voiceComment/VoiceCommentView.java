package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.framework.BaseView;

public class VoiceCommentView extends BaseView<VoiceCommentContract.Presenter> implements VoiceCommentContract.View {

    private VoiceCommentAdapter mAdapter;

    public VoiceCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    mAdapter = new VoiceCommentAdapter();
    mRvXXX.setItemAnimator(new DefaultItemAnimator());
    mRvXXX.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvXXX.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }
}
