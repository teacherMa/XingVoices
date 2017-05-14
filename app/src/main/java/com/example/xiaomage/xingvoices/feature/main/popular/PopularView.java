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
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice;

import java.util.List;

import butterknife.BindView;

public class PopularView extends BaseView<PopularContract.Presenter> implements PopularContract.View,OnItemClickListener<RemoteVoice> {

    @BindView(R.id.main_popular_rv)
    RecyclerView mMainPopularRv;

    private PopularAdapter mAdapter;

    public PopularView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
