package com.example.xiaomage.xingvoices.feature.main.collection;

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

public class CollectionView extends BaseView<CollectionContract.Presenter> implements CollectionContract.View {

    @BindView(R.id.main_collection_rv)
    RecyclerView mMainCollectionRv;

    private CollectionAdapter mAdapter;

    public CollectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new CollectionAdapter();
        mMainCollectionRv.setItemAnimator(new DefaultItemAnimator());
        mMainCollectionRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainCollectionRv.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_collection_view;
    }
}
