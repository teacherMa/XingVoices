package com.example.xiaomage.xingvoices.feature.main.collection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class CollectionFragment extends BaseFragment<CollectionPresenter> {

    @BindView(R.id.main_collection_view)
    CollectionView mMainCollectionView;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter(
                Injection.provideMainRepository(),
                mMainCollectionView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_collection_frag;
    }

}
