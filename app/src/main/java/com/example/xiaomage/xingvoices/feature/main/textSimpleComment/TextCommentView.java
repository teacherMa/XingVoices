package com.example.xiaomage.xingvoices.feature.main.textSimpleComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.ToLikeComEvent;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class TextCommentView extends BaseBusView<TextCommentContract.Presenter> implements TextCommentContract.View,
        OnItemClickListener<CommentBean> {

    @BindView(R.id.rv_text_com)
    RecyclerView mRvTextCom;

    private TextCommentAdapter mAdapter;
    private RemoteVoice mRemoteVoice;
    private int mModel;

    public TextCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {

    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new TextCommentAdapter();
        mAdapter.setOnClickListener(this);
        mRvTextCom.setItemAnimator(new DefaultItemAnimator());
        mRvTextCom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvTextCom.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_simple_text_com_view;
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

    @Override
    public void likeSuccess(String info) {
        BaseUtil.showToast(info + BaseUtil.getString(R.string.main_like_success));
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    public void setModel(int model) {
        mModel = model;
        if (mAdapter == null) {
            return;
        }
        mAdapter.setModel(mModel);
    }

    private void toLikeCom(String cId){
        getPresenter().likeIt(cId);
    }

    @Override
    public void onItemClick(CommentBean itemValue, int viewID, int position) {
        switch (viewID){
            case R.id.iv_com_like_it:
                toLikeCom(itemValue.getCid());
                break;
        }
    }
}
