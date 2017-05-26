package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.event.ChangeAnimEvent;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.MainViewInitEvent;
import com.example.xiaomage.xingvoices.event.StopPlayVoice;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

public class PopularView extends BaseBusView<PopularContract.Presenter> implements PopularContract.View, OnItemClickListener<RemoteVoice> {

    @BindView(R.id.main_popular_rv)
    RecyclerView mMainPopularRv;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.load_bar)
    ProgressBar mLoadBar;

    private PopularAdapter mAdapter;
    private int mCurPosition;
    private String mCurVoiceComId;
    private int mCurPage = 1;
    private boolean mIsloadingMore;

    public PopularView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {
        if (null == event) {
            return;
        }
        if (event instanceof MainViewInitEvent) {
            MainViewInitEvent initEvent = (MainViewInitEvent) event;
            if (!initEvent.isNeedInit()) {
                return;
            }
            getPresenter().requestAllPopularVoice(mCurPage);
            mSrlRefresh.setRefreshing(true);
        }
        if (event instanceof VHRecordEvent) {
            VHRecordEvent recordEvent = (VHRecordEvent) event;
            if (!Constants.ViewHolderTag.PopularVH.equals(recordEvent.getTag())) {
                return;
            }
            getPresenter().recordAudio(recordEvent.isToStart());
            return;
        }
        if (event instanceof VHAuditionEvent) {
            VHAuditionEvent auditionEvent = (VHAuditionEvent) event;
            if (!Constants.ViewHolderTag.PopularVH.equals(auditionEvent.getTag())) {
                return;
            }
            getPresenter().playVoice(mCurVoiceComId);
            return;
        }
        if (event instanceof VHPublishTextComEvent) {
            VHPublishTextComEvent textComEvent = (VHPublishTextComEvent) event;
            if (!Constants.ViewHolderTag.PopularVH.equals(textComEvent.getTag())) {
                return;
            }
            getPresenter().publishTextCom(textComEvent.getRemoteVoice().getVid(), textComEvent.getContent());
            return;
        }
        if (event instanceof VHPublishVoiceComEvent) {
            VHPublishVoiceComEvent voiceComEvent = (VHPublishVoiceComEvent) event;
            if (!Constants.ViewHolderTag.PopularVH.equals(voiceComEvent.getTag())) {
                return;
            }
            int length = Integer.parseInt(voiceComEvent.getComLength());
            getPresenter().publishVoiceCom(voiceComEvent.getRemoteVoice().getVid(), mCurVoiceComId, length);
            return;
        }
        if(event instanceof StopPlayVoice){
            StopPlayVoice stopPlayVoice = (StopPlayVoice)event;
            if(!stopPlayVoice.isToStop()){
                return;
            }
            getPresenter().toStopPlayVoice();
        }
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mMainPopularRv.setItemAnimator(new DefaultItemAnimator());
        mMainPopularRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainPopularRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int enable = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                mSrlRefresh.setEnabled(enable == 0);
                if (mIsloadingMore) {
                    super.onScrolled(recyclerView, dx, dy);
                    return;

                }
                if (!recyclerView.canScrollVertically(1)) {
                    mCurPage++;
                    mLoadBar.setVisibility(VISIBLE);
                    mIsloadingMore = true;
                    getPresenter().requestAllPopularVoice(mCurPage);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mAdapter = new PopularAdapter();
        mAdapter.setOnClickListener(this);

        mMainPopularRv.setAdapter(mAdapter);

        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().requestAllPopularVoice(mCurPage);
                mIsloadingMore = false;
            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_popular_view;
    }

    @Override
    public void loadData(List<RemoteVoice> data) {
        int origin = 0;
        if (null != mAdapter.getValueList()) {
            origin = mAdapter.getValueList().size();
        }
        mSrlRefresh.setRefreshing(false);
        mLoadBar.setVisibility(GONE);
        ((PopularAdapter) mMainPopularRv.getAdapter()).refreshData(data);
        if (mIsloadingMore) {
            mMainPopularRv.scrollToPosition(origin - 1);
        }
    }

    @Override
    public void downloadSuccess(String vId) {
        getPresenter().playVoice(vId);
        ChangeAnimEvent animEvent = new ChangeAnimEvent(mCurPosition, true);
        EventBus.getDefault().post(animEvent);
    }

    @Override
    public void playFinished() {
        ChangeAnimEvent event = new ChangeAnimEvent(mCurPosition, false);
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
    public void changeStateSuccess(String info) {
        BaseUtil.showToast(info);
    }

    @Override
    public void shieldResult(String info) {
        BaseUtil.showToast(info);
    }

    @Override
    public void onItemClick(RemoteVoice itemValue, int viewID, int position) {
        switch (viewID) {
            case R.id.iv_pic_of_voice:
                getPresenter().downloadVoice(itemValue.getReurl(), itemValue.getVid());
                break;
            case R.id.iv_follow:
                getPresenter().changeFollowState(itemValue.getUser().getUid(), 0);
                break;
            case R.id.tv_collection:
                getPresenter().toCollection(itemValue.getVid(), 0);
                break;
            case R.id.tv_add_to_blacklist:
                getPresenter().toShield(itemValue.getVid());
                break;
        }
    }

    public void refreshView() {
        getPresenter().requestAllPopularVoice(mCurPage);
        mIsloadingMore = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getPresenter().toStopPlayVoice();
    }
}
