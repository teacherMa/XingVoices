package com.example.xiaomage.xingvoices.feature.personal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.custom.view.WrapContentViewPager;
import com.example.xiaomage.xingvoices.feature.main.textSimpleComment.TextCommentFragment;
import com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment.VoiceCommentFragment;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.UserManager;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.*;

public class PersonalVH extends BaseViewHolder<RemoteVoice> {

    @BindView(R.id.civ_user_avatar)
    ImageView mCivUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.iv_follow)
    ImageView mIvFollow;
    @BindView(R.id.tv_voice_name)
    TextView mTvVoiceName;
    @BindView(R.id.tv_voice_length)
    TextView mTvVoiceLength;
    @BindView(R.id.iv_pic_of_voice)
    ImageView mIvPicOfVoice;
    @BindView(R.id.tv_browsed_times)
    TextView mTvBrowsedTimes;
    @BindView(R.id.tv_text_com)
    TextView mTvTextCom;
    @BindView(R.id.tv_voice_com)
    TextView mTvVoiceCom;
    @BindView(R.id.vp_comments)
    WrapContentViewPager mVpComments;

    private RemoteVoice mRemoteVoice;
    private List<Fragment> mFragments;

    public PersonalVH(Context context, ViewGroup root) {
        super(context, root, R.layout.personal_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {

        mRemoteVoice = itemValue;

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

        mIvFollow.setVisibility(INVISIBLE);

    }

    @OnClick(R.id.iv_follow)
    public void onMIvFollowClicked() {
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

        FragmentManager fragmentManager = ((PersonalActivity)getContext()).getSupportFragmentManager();

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

    public PersonalVH setId(){
        mVpComments.setId(View.generateViewId());
        return this;
    }
}
