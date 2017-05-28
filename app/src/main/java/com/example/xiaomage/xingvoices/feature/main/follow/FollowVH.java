package com.example.xiaomage.xingvoices.feature.main.follow;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.api.OnVpScrollListener;
import com.example.xiaomage.xingvoices.api.main.OnBottomCommentItemClickListener;
import com.example.xiaomage.xingvoices.api.main.OnBottomMenuItemClickListener;
import com.example.xiaomage.xingvoices.api.main.OnBottomShareItemClickListener;
import com.example.xiaomage.xingvoices.custom.view.BottomCommentView;
import com.example.xiaomage.xingvoices.custom.view.BottomMenu;
import com.example.xiaomage.xingvoices.custom.view.BottomShareView;
import com.example.xiaomage.xingvoices.custom.view.CircleImageView;
import com.example.xiaomage.xingvoices.custom.view.WrapContentViewPager;
import com.example.xiaomage.xingvoices.event.VH.VHAuditionEvent;
import com.example.xiaomage.xingvoices.event.VH.VHPublishTextComEvent;
import com.example.xiaomage.xingvoices.event.VH.VHPublishVoiceComEvent;
import com.example.xiaomage.xingvoices.event.VH.VHRecordEvent;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.feature.main.WatchPicActivity;
import com.example.xiaomage.xingvoices.feature.main.comment.CommentActivity;
import com.example.xiaomage.xingvoices.feature.main.textSimpleComment.TextCommentFragment;
import com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment.VoiceCommentFragment;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

import static android.view.View.INVISIBLE;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.ADD_TO_BLACK_LIST;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.COLLECTION;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.COMMENT;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.LOOK_UP_PIC;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.SHARE;

public class FollowVH extends BaseViewHolder<RemoteVoice> implements OnBottomMenuItemClickListener,
        OnBottomCommentItemClickListener, OnBottomShareItemClickListener, OnVpScrollListener {

    @BindView(R.id.civ_user_avatar)
    CircleImageView mCivUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.iv_follow)
    ImageView mIvFollow;
    @BindView(R.id.item_head)
    RelativeLayout mItemHead;
    @BindView(R.id.tv_voice_name)
    TextView mTvVoiceName;
    @BindView(R.id.tv_voice_length)
    TextView mTvVoiceLength;
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
    @BindView(R.id.vp_comments)
    WrapContentViewPager mVpComments;
    @BindView(R.id.tv_more_com)
    TextView mTvMoreCom;
    private OnItemClickListener<RemoteVoice> mOnItemClickListener;
    private RemoteVoice mRemoteVoice;
    private List<Fragment> mFragments;
    private int mPosition;
    private AnimationDrawable mAnimationDrawable;

    public FollowVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_follow_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {
        if (null == mAnimationDrawable) {
            mAnimationDrawable = new AnimationDrawable();
//            initAnimation();
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

        boolean isFollow = mRemoteVoice.getIs_focus() == 1;
        Intent intent = PersonalActivity.getNewIntent(xingVoiceUser, isFollow, getContext());
        getContext().startActivity(intent);
    }

    @OnClick(R.id.iv_follow)
    public void onMIvFollowClicked() {
        return;
        /*mOnItemClickListener.onItemClick(mRemoteVoice, R.id.iv_follow, FOLLOW);
        if(mRemoteVoice.getIs_focus() == 1){
            mIvFollow.setVisibility(INVISIBLE);
            return;
        }
        mIvFollow.setVisibility(VISIBLE);*/
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
        Intent intent = CommentActivity.getNewIntent(mRemoteVoice, getContext());
        getContext().startActivity(intent);
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
                bottomCommentView.setItemClickListener(this);
                View root = LayoutInflater.from(getContext()).inflate(R.layout.main_view, null);
                bottomCommentView.showAtLocation(root, Gravity.BOTTOM, 0, 0);
                break;
            case COLLECTION:
                mOnItemClickListener.onItemClick(mRemoteVoice, R.id.tv_collection, COLLECTION);
                break;
            case SHARE:
                BottomShareView bottomShareView = new BottomShareView(getContext());
                bottomShareView.setOnBottomShareItemClickListener(this);
                View shareRoot = LayoutInflater.from(getContext()).inflate(R.layout.main_view, null);
                bottomShareView.showAtLocation(shareRoot, Gravity.BOTTOM, 0, 0);
                break;
            case LOOK_UP_PIC:
                getContext().startActivity(WatchPicActivity.getIntent(getContext(), mRemoteVoice.getAllbackgrund()));
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

        mVpComments.setOnVpScrollListener(this);

        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
    }

    public FollowVH setId() {
        mVpComments.setId(View.generateViewId());
        return this;
    }

    @Override
    public void onBottomCommentItemClick(int position, String content) {
        switch (position) {
            case Constants.BottomComItem.START_RECORD:
                EventBus.getDefault().post(new VHRecordEvent(Constants.ViewHolderTag.FollowVH, true));
                break;
            case Constants.BottomComItem.STOP_RECORD:
                EventBus.getDefault().post(new VHRecordEvent(Constants.ViewHolderTag.FollowVH, false));
                break;
            case Constants.BottomComItem.TO_AUDITION:
                EventBus.getDefault().post(new VHAuditionEvent(Constants.ViewHolderTag.FollowVH));
                break;
            case Constants.BottomComItem.STOP_AUDITION:
                EventBus.getDefault().post(new VHAuditionEvent(Constants.ViewHolderTag.FollowVH));
                break;
            case Constants.BottomComItem.SEND_TEXT_COM:
                EventBus.getDefault().post(new VHPublishTextComEvent(Constants.ViewHolderTag.FollowVH,
                        content, mRemoteVoice));
                break;
            case Constants.BottomComItem.SEND_VOICE_COM:
                EventBus.getDefault().post(new VHPublishVoiceComEvent(Constants.ViewHolderTag.FollowVH, mRemoteVoice, content));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBottomShareItemClick(int position) {
        switch (position) {
            case Constants.BottomShareItem.QQ_SHARE:
                ShareUtil.shareMedia(getContext(), SharePlatform.QQ, mRemoteVoice.getTitle(),
                        BaseUtil.getString(R.string.vista_share_title), mRemoteVoice.getReurl(),
                        BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_main_voice_down_like),
                        new ShareListener() {
                            @Override
                            public void shareSuccess() {
                                BaseUtil.showToast(BaseUtil.getString(R.string.main_share_success));
                            }

                            @Override
                            public void shareFailure(Exception e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void shareCancel() {
                                BaseUtil.showToast(BaseUtil.getString(R.string.main_share_cancel));
                            }
                        });
                break;
            case Constants.BottomShareItem.SINA_SHARE:
                ShareUtil.shareMedia(getContext(), SharePlatform.WEIBO, mRemoteVoice.getTitle(),
                        BaseUtil.getString(R.string.vista_share_title), mRemoteVoice.getReurl(),
                        BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_main_voice_down_like),
                        new ShareListener() {
                            @Override
                            public void shareSuccess() {
                                BaseUtil.showToast(BaseUtil.getString(R.string.main_share_success));
                            }

                            @Override
                            public void shareFailure(Exception e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void shareCancel() {
                                BaseUtil.showToast(BaseUtil.getString(R.string.main_share_cancel));
                            }
                        });
                break;
            case Constants.BottomShareItem.WECHAT_SHARE:
                ShareUtil.shareMedia(getContext(), SharePlatform.WX, mRemoteVoice.getTitle(),
                        BaseUtil.getString(R.string.vista_share_title), mRemoteVoice.getReurl(),
                        BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_main_voice_down_like),
                        new ShareListener() {
                            @Override
                            public void shareSuccess() {
                                BaseUtil.showToast(BaseUtil.getString(R.string.main_share_success));
                            }

                            @Override
                            public void shareFailure(Exception e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void shareCancel() {
                                BaseUtil.showToast(BaseUtil.getString(R.string.main_share_cancel));
                            }
                        });
                break;
        }
    }

    @Override
    public void onVpScroll(boolean isScroll) {
        if (isScroll) {
            mOnItemClickListener.onItemClick(mRemoteVoice, Constants.ViewPagerScroll.VP_IS_SCROLL, 0);
            return;
        }
        mOnItemClickListener.onItemClick(mRemoteVoice,Constants.ViewPagerScroll.VP_STOP_SCROLL,0);
    }
}
