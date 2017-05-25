package com.example.xiaomage.xingvoices.feature.main.popular;

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
import com.example.xiaomage.xingvoices.event.MainViewInitEvent;
import com.example.xiaomage.xingvoices.event.ChangeAnimEvent;
import com.example.xiaomage.xingvoices.event.VH.VHAuditionEvent;
import com.example.xiaomage.xingvoices.event.VH.VHPublishTextComEvent;
import com.example.xiaomage.xingvoices.event.VH.VHPublishVoiceComEvent;
import com.example.xiaomage.xingvoices.event.VH.VHRecordEvent;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class PopularView extends BaseBusView<PopularContract.Presenter> implements PopularContract.View,OnItemClickListener<RemoteVoice> {

    @BindView(R.id.main_popular_rv)
    RecyclerView mMainPopularRv;

    private PopularAdapter mAdapter;
    private int mCurPosition;
    private String mCurVoiceComId;

    public PopularView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {
        if(null == event){
            return;
        }
        if(event instanceof MainViewInitEvent){
            MainViewInitEvent initEvent = (MainViewInitEvent) event;
            if(!initEvent.isNeedInit()){
                return;
            }
            getPresenter().requestAllPopularVoice();
        }
        if(event instanceof VHRecordEvent){
            VHRecordEvent recordEvent = (VHRecordEvent) event;
            if(!Constants.ViewHolderTag.PopularVH.equals(recordEvent.getTag())){
                return;
            }
            getPresenter().recordAudio(recordEvent.isToStart());
            return;
        }
        if(event instanceof VHAuditionEvent){
            VHAuditionEvent auditionEvent = (VHAuditionEvent) event;
            if(!Constants.ViewHolderTag.PopularVH.equals(auditionEvent.getTag())){
                return;
            }
            getPresenter().playVoice(mCurVoiceComId);
            return;
        }
        if (event instanceof VHPublishTextComEvent){
            VHPublishTextComEvent textComEvent = (VHPublishTextComEvent)event;
            if(!Constants.ViewHolderTag.PopularVH.equals(textComEvent.getTag())){
                return;
            }
            getPresenter().publishTextCom(textComEvent.getRemoteVoice().getVid(),textComEvent.getContent());
            return;
        }
        if(event instanceof VHPublishVoiceComEvent){
            VHPublishVoiceComEvent voiceComEvent = (VHPublishVoiceComEvent)event;
            if(!Constants.ViewHolderTag.PopularVH.equals(voiceComEvent.getTag())){
                return;
            }
            int length = Integer.parseInt(voiceComEvent.getComLength());
            getPresenter().publishVoiceCom(voiceComEvent.getRemoteVoice().getVid(),mCurVoiceComId,length);
        }
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mMainPopularRv.setItemAnimator(new DefaultItemAnimator());
        mMainPopularRv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new PopularAdapter();
        mAdapter.setOnClickListener(this);

        mMainPopularRv.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_popular_view;
    }

    @Override
    public void loadData(List<RemoteVoice> data) {
        ((PopularAdapter)mMainPopularRv.getAdapter()).refreshData(data);
    }

    @Override
    public void downloadSuccess(String vId) {
        getPresenter().playVoice(vId);
        ChangeAnimEvent animEvent = new ChangeAnimEvent(mCurPosition,true);
        EventBus.getDefault().post(animEvent);
    }

    @Override
    public void playFinished() {
        ChangeAnimEvent event = new ChangeAnimEvent(mCurPosition,false);
        EventBus.getDefault().post(event);
    }

    @Override
    public void recordSuccess(String id) {
        mCurVoiceComId = id;
    }

    @Override
    public void commentSuccess(String info) {
        BaseUtil.showToast(info);
    }

    @Override
    public void onItemClick(RemoteVoice itemValue, int viewID, int position) {
        switch (viewID){
            case R.id.iv_pic_of_voice:
                getPresenter().downloadVoice(itemValue.getReurl(),itemValue.getVid());
                break;
        }
    }

}
