package com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment;

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
import com.example.xiaomage.xingvoices.event.PlayVoiceComEvent;
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

public class VoiceCommentView extends BaseBusView<VoiceCommentContract.Presenter> implements VoiceCommentContract.View,
        OnItemClickListener<CommentBean> {

    @BindView(R.id.rv_voice_com)
    RecyclerView mRvVoiceCom;

    private VoiceCommentAdapter mAdapter;
    private RemoteVoice mRemoteVoice;
    private int mModel;

    public VoiceCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {
        if (event == null) {
            return;
        }

    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new VoiceCommentAdapter();
        mAdapter.setOnClickListener(this);
        mRvVoiceCom.setItemAnimator(new DefaultItemAnimator());
        mRvVoiceCom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvVoiceCom.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_simple_voice_com_view;
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

    private void toPlayCom(CommentBean commentBean){
        getPresenter().playComVoice(commentBean);
    }

    @Override
    public void onItemClick(CommentBean itemValue, int viewID, int position) {
        switch (viewID){
            case R.id.iv_com_content:
                toPlayCom(itemValue);
                break;
            case R.id.iv_com_like_it:
                toLikeCom(itemValue.getCid());
                break;
        }
    }
}
