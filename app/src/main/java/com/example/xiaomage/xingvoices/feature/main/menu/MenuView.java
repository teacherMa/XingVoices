package com.example.xiaomage.xingvoices.feature.main.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.custom.view.CircleImageView;
import com.example.xiaomage.xingvoices.custom.view.RatioLayout;
import com.example.xiaomage.xingvoices.event.MenuCloseEvent;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuView extends BaseView<MenuContract.Presenter> implements MenuContract.View {


    @BindView(R.id.iv_back_content)
    ImageView mIvBackContent;
    @BindView(R.id.menu_user_avatar)
    CircleImageView mMenuUserAvatar;
    @BindView(R.id.menu_user_name)
    TextView mMenuUserName;
    @BindView(R.id.tv_follow_number)
    TextView mTvFollowNumber;
    @BindView(R.id.tv_follow_string)
    TextView mTvFollowString;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;
    @BindView(R.id.tv_fans_string)
    TextView mTvFansString;
    @BindView(R.id.rl_ph_head)
    RatioLayout mRlPhHead;
    @BindView(R.id.menu_my_publish)
    ImageView mMenuMyPublish;
    @BindView(R.id.menu_my_draft)
    ImageView mMenuMyDraft;
    @BindView(R.id.menu_sys_message)
    ImageView mMenuSysMessage;
    @BindView(R.id.menu_my_setting)
    ImageView mMenuMySetting;
    @BindView(R.id.rl_menu_setting_list)
    RelativeLayout mRlMenuSettingList;

    public MenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_menu_view;
    }

    @OnClick({R.id.iv_back_content, R.id.menu_user_avatar, R.id.menu_user_name,
            R.id.tv_follow_number, R.id.tv_fans_num, R.id.menu_my_publish,
            R.id.menu_my_draft, R.id.menu_sys_message, R.id.menu_my_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_content:
                MenuCloseEvent event = new MenuCloseEvent(false);
                EventBus.getDefault().post(event);
                break;
            case R.id.menu_user_avatar:
                break;
            case R.id.menu_user_name:
                break;
            case R.id.tv_follow_number:
                break;
            case R.id.tv_fans_num:
                break;
            case R.id.menu_my_publish:
                break;
            case R.id.menu_my_draft:
                break;
            case R.id.menu_sys_message:
                break;
            case R.id.menu_my_setting:
                break;
        }
        BaseUtil.showToast(""+view.getId());
    }
}
