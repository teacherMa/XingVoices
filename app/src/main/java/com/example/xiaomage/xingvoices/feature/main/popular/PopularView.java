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
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class PopularView extends BaseBusView<PopularContract.Presenter> implements PopularContract.View,OnItemClickListener<RemoteVoice> {

    @BindView(R.id.main_popular_rv)
    RecyclerView mMainPopularRv;

    private PopularAdapter mAdapter;
    private XingVoiceUserResp mXingVoiceUserResp;

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
            mXingVoiceUserResp = initEvent.getResp();

            getPresenter().requestVoiceData(mXingVoiceUserResp);
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
    public void onItemClick(RemoteVoice itemValue, int viewID, int position) {

    }
}
