package com.example.xiaomage.xingvoices.feature.main.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.MenuCloseEvent;
import com.example.xiaomage.xingvoices.event.MainViewInitEvent;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.feature.main.menu.systemMessage.MessageActivity;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.UserManager;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUserResp;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.FileUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuView extends BaseBusView<MenuContract.Presenter> implements MenuContract.View {

    @BindView(R.id.iv_back_content)
    ImageView mIvBackContent;
    @BindView(R.id.menu_user_avatar)
    ImageView mMenuUserAvatar;
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

    private BasicUserInfo mBasicUserInfo;

    public MenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {
        if(null == event){
            return;
        }
        if(event instanceof MainViewInitEvent){
            MainViewInitEvent initEvent = (MainViewInitEvent) event;
            if(!initEvent.isNeedInit()){
                return;
            }
            getPresenter().getUserInfo();
        }
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
                XingVoiceUser xingVoiceUser = new XingVoiceUser();

                xingVoiceUser.setHeadpic(UserManager.getInstance().getCurrentUser().getAvatar());
                xingVoiceUser.setNickname(UserManager.getInstance().getCurrentUser().getName());
                xingVoiceUser.setUid(UserManager.getInstance().getCurrentUser().getId());

                Intent intent = PersonalActivity.getNewIntent(xingVoiceUser, getContext());
                getContext().startActivity(intent);
                break;
            case R.id.menu_my_draft:
                break;
            case R.id.menu_sys_message:
                getContext().startActivity(MessageActivity.getIntent(getContext()));
                break;
            case R.id.menu_my_setting:
                getContext().startActivity(SettingActivity.getIntent(getContext()));
                break;
        }
    }

    public void setBasicUserInfo(BasicUserInfo basicUserInfo) {

        if(null == basicUserInfo){
            return;
        }

        mBasicUserInfo = basicUserInfo;

        BaseUtil.loadCirclePic(mBasicUserInfo.getHeadpic()).into(mMenuUserAvatar);

        mMenuUserName.setText(basicUserInfo.getNickname());

        mTvFollowNumber.setText(String.valueOf(basicUserInfo.getGuanzhu()));

        mTvFansNum.setText(String.valueOf(basicUserInfo.getFensi()));
    }
}
