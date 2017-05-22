package com.example.xiaomage.xingvoices.feature.main.textComment;

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

public class TextCommentView extends BaseView<TextCommentContract.Presenter> implements TextCommentContract.View {

    @BindView(R.id.rv_text_com)
    RecyclerView mRvTextCom;

    private TextCommentAdapter mAdapter;
    private RemoteVoice mRemoteVoice;

    public TextCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new TextCommentAdapter();
        mRvTextCom.setItemAnimator(new DefaultItemAnimator());
        mRvTextCom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvTextCom.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_text_com_view;
    }

    @Override
    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    @Override
    public void updateData(List<CommentBean> been) {
        if(null == mRvTextCom.getAdapter()){
            TextCommentAdapter adapter = new TextCommentAdapter();
            mRvTextCom.setAdapter(adapter);
        }
        ((TextCommentAdapter)mRvTextCom.getAdapter()).refreshData(been);
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }
}
