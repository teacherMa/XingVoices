package com.example.xiaomage.xingvoices.feature.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.custom.view.SlidingMenu;
import com.example.xiaomage.xingvoices.framework.BaseView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainView extends BaseView<MainContract.Presenter> implements MainContract.View {


    @BindView(R.id.iv_main_nav)
    ImageView mIvMainNav;
    @BindView(R.id.tv_main_follow)
    TextView mTvMainFollow;
    @BindView(R.id.tv_main_popular)
    TextView mTvMainPopular;
    @BindView(R.id.tv_main_collection)
    TextView mTvMainCollection;
    @BindView(R.id.iv_main_record)
    ImageView mIvMainRecord;
    @BindView(R.id.main_view_vp)
    ViewPager mMainViewVp;
    @BindView(R.id.left_menu)
    LinearLayout mLeftMenu;
    @BindView(R.id.main_sliding_menu)
    SlidingMenu mMainSlidingMenu;
    @BindView(R.id.nav_new_message)
    FrameLayout mNavNewMessage;
    @BindView(R.id.content)
    LinearLayout mContent;


    public MainView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_view;
    }

    @OnClick({R.id.iv_main_nav, R.id.tv_main_follow, R.id.tv_main_popular, R.id.tv_main_collection, R.id.iv_main_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_nav:
                if (mMainSlidingMenu.isOpen()) {
                    mMainSlidingMenu.closeMenu();
                } else {
                    mMainSlidingMenu.openMenu();
                }
                break;
            case R.id.tv_main_follow:
                break;
            case R.id.tv_main_popular:
                break;
            case R.id.tv_main_collection:
                break;
            case R.id.iv_main_record:
                break;
        }
    }
}
