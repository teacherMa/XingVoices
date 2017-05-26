package com.example.xiaomage.xingvoices.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.custom.view.SlidingMenu;
import com.example.xiaomage.xingvoices.event.MenuCloseEvent;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.MainViewInitEvent;
import com.example.xiaomage.xingvoices.event.ShowNewMessage;
import com.example.xiaomage.xingvoices.event.StopPlayVoice;
import com.example.xiaomage.xingvoices.feature.main.collection.CollectionFragment;
import com.example.xiaomage.xingvoices.feature.main.follow.FollowFragment;
import com.example.xiaomage.xingvoices.feature.main.menu.MenuFragment;
import com.example.xiaomage.xingvoices.feature.main.popular.PopularFragment;
import com.example.xiaomage.xingvoices.feature.record.RecordActivity;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.model.bean.WxBean.WxUserInfo;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainView extends BaseBusView<MainContract.Presenter> implements MainContract.View {

    private static final String MENU_FRAGMENT = "menu fragment";

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
    @BindView(R.id.main_view_toolbar)
    Toolbar mMainViewToolbar;
    @BindView(R.id.content)
    RelativeLayout mContent;

    private List<Fragment> mVpFragments;
    private TextView mTvLastItem;
    private WxUserInfo mWxUserInfo;
    private XingVoiceUserResp mResp;

    public void setWxUserInfo(WxUserInfo wxUserInfo) {
        mWxUserInfo = wxUserInfo;
    }

    public MainView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {

        if(null == event){
            return;
        }

        if (event instanceof MenuCloseEvent){
            MenuCloseEvent menuCloseEvent = (MenuCloseEvent) event;
            if(menuCloseEvent.isClosed()){
                return;
            }
            mMainSlidingMenu.closeMenu();
        }

        if(event instanceof ShowNewMessage){
            ShowNewMessage newMessage = (ShowNewMessage)event;
            if(newMessage.isPublishNew()){
                mNavNewMessage.setVisibility(VISIBLE);
            }else {
                mNavNewMessage.setVisibility(GONE);
            }
        }
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_view;
    }

    @OnClick({R.id.iv_main_nav, R.id.tv_main_follow, R.id.tv_main_popular, R.id.tv_main_collection,
            R.id.iv_main_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_nav:
                mMainSlidingMenu.toggleMenu();
                break;
            case R.id.tv_main_popular:
                mMainViewVp.setCurrentItem(0);
                mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                mTvMainPopular.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                mTvLastItem = mTvMainPopular;
                break;
            case R.id.tv_main_follow:
                mMainViewVp.setCurrentItem(1);
                mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                mTvMainFollow.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                mTvLastItem = mTvMainFollow;
                break;
            case R.id.tv_main_collection:
                mMainViewVp.setCurrentItem(2);
                mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                mTvMainCollection.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                mTvLastItem = mTvMainCollection;
                break;
            case R.id.iv_main_record:
                int phoneSDK = Build.VERSION.SDK_INT;
                if(phoneSDK>=23) {
                    MainActivityPermissionsDispatcher.tryRecordWithCheck((MainActivity) getContext());
                }else {
                    toRecord();
                }
                break;
            case R.id.content:
                if (mMainSlidingMenu.isOpen()) {
                    mMainSlidingMenu.closeMenu();
                }
                break;
            default:
                break;
        }
    }

    private void prepareFragments() {

        mVpFragments = new ArrayList<>();
        mVpFragments.add(PopularFragment.getNewInstance(getContext()));
        mVpFragments.add(FollowFragment.getNewInstance(getContext()));
        mVpFragments.add(CollectionFragment.getNewInstance(getContext()));

        FragmentManager manager = ((MainActivity) getContext()).getSupportFragmentManager();

        if (null == manager.findFragmentByTag(MENU_FRAGMENT)) {
            Fragment fragment = MenuFragment.getNewInstance(getContext());
            manager.beginTransaction().add(R.id.left_menu, fragment, MENU_FRAGMENT).commit();
        }
    }

    private void initViewPager() {

        FragmentManager manager = ((MainActivity) getContext()).getSupportFragmentManager();

        mMainViewVp.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return mVpFragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }


        });

        mMainViewVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                EventBus.getDefault().post(new StopPlayVoice(true));
                switch (position) {
                    case 0:
                        mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        mTvMainPopular.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvLastItem = mTvMainPopular;
                        break;
                    case 1:
                        mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        mTvMainFollow.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvLastItem = mTvMainFollow;
                        break;
                    case 2:
                        mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        mTvMainCollection.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvLastItem = mTvMainCollection;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mMainViewVp.setOffscreenPageLimit(2);

        mMainViewVp.setCurrentItem(0);
        mTvLastItem = mTvMainPopular;
        mTvLastItem.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));

    }

    @Override
    public WxUserInfo getWxUserInfo() {
        return mWxUserInfo;
    }

    @Override
    public void initMainUi() {
        mMainSlidingMenu.openMenu();
        int phoneSDK = Build.VERSION.SDK_INT;
        if(phoneSDK>=23){
            MainActivityPermissionsDispatcher.tryInitWithCheck((MainActivity)getContext());
        }else {
            toInitFragmentAndVp();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainViewInitEvent initEvent = new MainViewInitEvent(true);
                EventBus.getDefault().post(initEvent);
            }
        }).start();
    }

    public void toRecord(){
        Intent intent = RecordActivity.getNewIntent(getContext());
        (getContext()).startActivity(intent);
    }

    public void toInitFragmentAndVp(){
        prepareFragments();
        initViewPager();
    }

    public boolean closeMenu(){
        boolean isOpen = mMainSlidingMenu.isOpen();
        mMainSlidingMenu.closeMenu();
        return isOpen;
    }
}
