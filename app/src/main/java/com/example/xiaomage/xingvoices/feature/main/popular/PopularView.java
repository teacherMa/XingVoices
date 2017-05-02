package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;

import butterknife.BindView;

public class PopularView extends BaseView<PopularContract.Presenter> implements PopularContract.View {

    @BindView(R.id.main_popular_rv)
    RecyclerView mMainPopularRv;

    private PopularAdapter mAdapter;

    public PopularView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new PopularAdapter();
        mMainPopularRv.setItemAnimator(new DefaultItemAnimator());
        mMainPopularRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainPopularRv.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_popular_view;
    }
}
