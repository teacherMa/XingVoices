package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.content.Intent;
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
import com.example.xiaomage.xingvoices.custom.view.WrapContentViewPager;
import com.example.xiaomage.xingvoices.custom.view.bottomMenu.BottomMenu;
import com.example.xiaomage.xingvoices.feature.main.MainActivity;
import com.example.xiaomage.xingvoices.feature.main.textComment.TextCommentFragment;
import com.example.xiaomage.xingvoices.feature.main.voiceComment.VoiceCommentFragment;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.ADD_TO_BLACK_LIST;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.COLLECTION;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.COMMENT;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.LOOK_UP_PIC;
import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.SHARE;
import static com.example.xiaomage.xingvoices.utils.Constants.MainPopularItem.FOLLOW;

public class PopularVH extends BaseViewHolder<RemoteVoice> implements OnBottomMenuItemClickListener {

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

    private OnItemClickListener<RemoteVoice> mOnItemClickListener;
    private RemoteVoice mRemoteVoice;
    private List<Fragment> mFragments;

    public PopularVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_popular_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {

        mOnItemClickListener = listener;
        mRemoteVoice = itemValue;

        BaseUtil.loadCirclePic(itemValue.getUser().getHeadpic()).into(mCivUserAvatar);

        mTvUserName.setText(itemValue.getUser().getNickname());

        if (itemValue.getIs_focus() == Constants.FollowType.Followed) {
            mIvFollow.setVisibility(View.INVISIBLE);
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

    @Override
    public void onBottomItemClick(int position) {
        switch (position) {
            case COMMENT:
                // TODO: 2017/5/14
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
        textCommentFragment.setModel(Constants.CommentItemType.SIMPLE);
        textCommentFragment.setRemoteVoice(mRemoteVoice);
        mFragments.add(textCommentFragment);

        VoiceCommentFragment voiceCommentFragment = new VoiceCommentFragment();
        voiceCommentFragment.setModel(Constants.CommentItemType.SIMPLE);
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

}
