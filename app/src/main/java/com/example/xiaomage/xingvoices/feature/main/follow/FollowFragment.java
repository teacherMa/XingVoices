package com.example.xiaomage.xingvoices.feature.main.follow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class FollowFragment extends BaseFragment<FollowPresenter> {
    @BindView(R.id.main_follow_view)
    FollowView mMainFollowView;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected FollowPresenter createPresenter() {
        return new FollowPresenter(
                Injection.provideMainRepository(),
                mMainFollowView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_follow_frag;
    }

}
