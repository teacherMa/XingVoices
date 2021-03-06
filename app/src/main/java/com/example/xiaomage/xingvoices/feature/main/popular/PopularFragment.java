package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseFragment;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class PopularFragment extends BaseFragment<PopularPresenter> {

    public static PopularFragment getNewInstance(Context context){
        return new PopularFragment();
    }

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

    @Override
    public void onResume() {
        super.onResume();
        mMainPopularView.refreshView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
