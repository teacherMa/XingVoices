package com.example.xiaomage.xingvoices.feature.main.follow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

import java.util.List;

import butterknife.BindView;

public class FollowView extends BaseView<FollowContract.Presenter> implements FollowContract.View {

    @BindView(R.id.main_follow_rv)
    RecyclerView mMainFollowRv;

    private FollowAdapter mAdapter;

    public FollowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new FollowAdapter();
        mMainFollowRv.setItemAnimator(new DefaultItemAnimator());
        mMainFollowRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainFollowRv.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_follow_view;
    }

    @Override
    public void loadData(List<RemoteVoice> data) {
        ((FollowAdapter)mMainFollowRv.getAdapter()).refreshData(data);
    }
}
