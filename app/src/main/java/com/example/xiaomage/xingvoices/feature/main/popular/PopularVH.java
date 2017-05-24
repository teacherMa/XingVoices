package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.api.main.OnBottomMenuItemClickListener;
import com.example.xiaomage.xingvoices.custom.view.BottomCommentView;
import com.example.xiaomage.xingvoices.custom.view.WrapContentViewPager;
import com.example.xiaomage.xingvoices.custom.view.BottomMenu;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.ChangeAnimEvent;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.feature.main.textComment.TextCommentFragment;
import com.example.xiaomage.xingvoices.feature.main.voiceComment.VoiceCommentFragment;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.ADD_TO_BLACK_LIST;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.COLLECTION;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.COMMENT;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.LOOK_UP_PIC;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.SHARE;
import static com.example.xiaomage.xingvoices.utils.Constants.MainPopularItem.FOLLOW;

public class PopularVH extends BaseViewHolder<RemoteVoice> implements OnBottomMenuItemClickListener {

    private static final int DURATION = 500;

    @BindView(R.id.civ_user_avatar)
    ImageView mCivUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.iv_follow)
    ImageView mIvFollow;
    @BindView(R.id.item_head)
    RelativeLayout mItemHead;
    @BindView(R.id.iv_pic_of_voice)
    ImageView mIvPicOfVoice;
    @BindView(R.id.iv_browsed)
    ImageView mIvBrowsed;
    @BindView(R.id.tv_browsed_times)
    TextView mTvBrowsedTimes;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.bar_basic_state)
    RelativeLayout mBarBasicState;
    @BindView(R.id.tv_text_com)
    TextView mTvTextCom;
    @BindView(R.id.tv_voice_com)
    TextView mTvVoiceCom;
    @BindView(R.id.tv_more_com)
    TextView mTvMoreCom;
    @BindView(R.id.tv_voice_name)
    TextView mTvVoiceName;
    @BindView(R.id.tv_voice_length)
    TextView mTvVoiceLength;
    @BindView(R.id.vp_comments)
    WrapContentViewPager mVpComments;
    @BindView(R.id.iv_play_anim)
    ImageView mIvPlayAnim;

    private OnItemClickListener<RemoteVoice> mOnItemClickListener;
    private RemoteVoice mRemoteVoice;
    private List<Fragment> mFragments;
    private int mPosition;
    private AnimationDrawable mAnimationDrawable;

    public PopularVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_popular_item);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event){
        if(null == event){
            return;
        }
        if(event instanceof ChangeAnimEvent){
            ChangeAnimEvent changeAnimEvent = (ChangeAnimEvent)event;
            if(mPosition != changeAnimEvent.getPosition()){
                return;
            }
            if(changeAnimEvent.isShouldStart()){
                startAnim();
                return;
            }

            stopAnim();
        }
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {

        if (null == mAnimationDrawable) {
            mAnimationDrawable = new AnimationDrawable();
            initAnimation();
        }

        mOnItemClickListener = listener;
        mRemoteVoice = itemValue;
        mPosition = position;

        BaseUtil.loadCirclePic(itemValue.getUser().getHeadpic()).into(mCivUserAvatar);

        mTvUserName.setText(itemValue.getUser().getNickname());

        if (itemValue.getIs_focus() == Constants.FollowType.Followed) {
            mIvFollow.setVisibility(INVISIBLE);
        }

        BaseUtil.load(itemValue.getBackgrund()).into(mIvPicOfVoice);

        mTvVoiceName.setText(itemValue.getTitle());
        mTvVoiceName.bringToFront();

        int min = itemValue.getLength() / 60;
        int sec = itemValue.getLength() % 60;
        mTvVoiceLength.setText(min + "'" + sec + "''");
        mTvVoiceLength.bringToFront();

        mTvBrowsedTimes.setText(String.valueOf(itemValue.getPlay_num()));

        prepareFragment();
        initViewPager();
    }

    @OnClick(R.id.civ_user_avatar)
    public void onMCivUserAvatarClicked() {

        XingVoiceUser xingVoiceUser = new XingVoiceUser();

        xingVoiceUser.setHeadpic(mRemoteVoice.getUser().getHeadpic());
        xingVoiceUser.setNickname(mRemoteVoice.getUser().getNickname());
        xingVoiceUser.setUid(mRemoteVoice.getUser().getUid());

        Intent intent = PersonalActivity.getNewIntent(xingVoiceUser, getContext());
        getContext().startActivity(intent);
    }

    @OnClick(R.id.iv_follow)
    public void onMIvFollowClicked() {
        mOnItemClickListener.onItemClick(mRemoteVoice, R.id.iv_follow, FOLLOW);
    }

    @OnClick(R.id.iv_more)
    public void onMIvMoreClicked() {
        BottomMenu window = new BottomMenu(getContext());
        View root = LayoutInflater.from(getContext()).inflate(R.layout.main_view, null);
        window.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        window.setOnBottomMenuItemClickListener(this);
    }

    @OnClick(R.id.tv_text_com)
    public void onMTvTextComClicked() {
        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
        mVpComments.setCurrentItem(0);
    }

    @OnClick(R.id.tv_voice_com)
    public void onMTvVoiceComClicked() {
        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
        mVpComments.setCurrentItem(1);
    }

    @OnClick(R.id.tv_more_com)
    public void onMTvMoreComClicked() {
    }

    @OnClick(R.id.iv_pic_of_voice)
    public void onViewClicked() {
        mOnItemClickListener.onItemClick(mRemoteVoice, R.id.iv_pic_of_voice, mPosition);
    }

    @Override
    public void onBottomItemClick(int position) {
        switch (position) {
            case COMMENT:
                BottomCommentView bottomCommentView = new BottomCommentView(getContext());
                View root = LayoutInflater.from(getContext()).inflate(R.layout.main_view, null);
                bottomCommentView.showAtLocation(root,Gravity.BOTTOM,0,0);
                break;
            case COLLECTION:
                mOnItemClickListener.onItemClick(mRemoteVoice, R.id.tv_collection, COLLECTION);
                break;
            case SHARE:
                // TODO: 2017/5/14
                break;
            case LOOK_UP_PIC:
                // TODO: 2017/5/14
                break;
            case ADD_TO_BLACK_LIST:
                mOnItemClickListener.onItemClick(mRemoteVoice, R.id.tv_add_to_blacklist, ADD_TO_BLACK_LIST);
        }
    }

    private void prepareFragment() {
        mFragments = new ArrayList<>();

        TextCommentFragment textCommentFragment = new TextCommentFragment();
        textCommentFragment.setRemoteVoice(mRemoteVoice);
        mFragments.add(textCommentFragment);

        VoiceCommentFragment voiceCommentFragment = new VoiceCommentFragment();
        voiceCommentFragment.setRemoteVoice(mRemoteVoice);
        mFragments.add(voiceCommentFragment);

    }

    private void initViewPager() {

        FragmentManager fragmentManager = ((MainActivity) getContext()).getSupportFragmentManager();

        mVpComments.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mVpComments.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        break;
                    case 1:
                        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mVpComments.setCurrentItem(0);
        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
    }

    public PopularVH setId() {
        mVpComments.setId(View.generateViewId());
        return this;
    }

    private void stopAnim(){
        mAnimationDrawable.stop();
        mIvPlayAnim.setVisibility(INVISIBLE);
    }

    private void startAnim(){

        mIvPlayAnim.setVisibility(VISIBLE);
        mIvPlayAnim.bringToFront();
        mIvPlayAnim.setBackgroundDrawable(mAnimationDrawable);

        if (null == mAnimationDrawable){
            mAnimationDrawable = new AnimationDrawable();
            initAnimation();
        }

        if(mAnimationDrawable.isRunning()){
            mAnimationDrawable.stop();
        }

        mAnimationDrawable.run();
    }

    private void initAnimation(){
        List<Drawable> drawables = new ArrayList<>();
        drawables.add(BaseUtil.getDrawable(R.drawable.ic_volume_s));
        drawables.add(BaseUtil.getDrawable(R.drawable.ic_volume_m));
        drawables.add(BaseUtil.getDrawable(R.drawable.ic_volume_l));

        for (int i = 0; i < drawables.size(); i++) {
            mAnimationDrawable.addFrame(drawables.get(i), DURATION);
        }
        mAnimationDrawable.setOneShot(false);
        mIvPlayAnim.setBackgroundDrawable(mAnimationDrawable);
    }
}
