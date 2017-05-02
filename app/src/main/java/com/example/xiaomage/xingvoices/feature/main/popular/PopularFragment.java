package com.example.xiaomage.xingvoices.feature.main.popular;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class PopularFragment extends BaseFragment<PopularPresenter> {
    @BindView(R.id.main_popular_view)
    PopularView mMainPopularView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected PopularPresenter createPresenter() {
        return new PopularPresenter(
                Injection.provideMainRepository(),
                mMainPopularView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_popular_frag;
    }

}
