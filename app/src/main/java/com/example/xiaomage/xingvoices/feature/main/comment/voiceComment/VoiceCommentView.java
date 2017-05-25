package com.example.xiaomage.xingvoices.feature.main.comment.voiceComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.PlayVoiceComEvent;
import com.example.xiaomage.xingvoices.event.ToLikeComEvent;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class VoiceCommentView extends BaseBusView<VoiceCommentContract.Presenter> implements VoiceCommentContract.View {

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

        if(event instanceof ToLikeComEvent){
            ToLikeComEvent likeComEvent = (ToLikeComEvent)event;
            if(!likeComEvent.getTag().equals(Constants.ViewHolderTag.VoiceCommentVH)){
                return;
            }
            if (likeComEvent.getId() == null) {
                return;
            }
            toLikeCom(likeComEvent.getId());
        }

        if(event instanceof PlayVoiceComEvent){
            PlayVoiceComEvent playVoiceComEvent = (PlayVoiceComEvent)event;
            if(!playVoiceComEvent.getTag().equals(Constants.ViewHolderTag.VoiceCommentVH)){
                return;
            }
            if(null == playVoiceComEvent.getCommentBean()){
                return;
            }
            toPlayCom(playVoiceComEvent.getCommentBean());
        }
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
    }

    private void toLikeCom(String cId){
        getPresenter().likeIt(cId);
    }

    private void toPlayCom(CommentBean commentBean){
        getPresenter().playVoiceCom(commentBean);
    }
}
