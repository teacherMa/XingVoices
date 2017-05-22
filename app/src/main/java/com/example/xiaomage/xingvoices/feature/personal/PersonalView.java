package com.example.xiaomage.xingvoices.feature.personal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.BasicUserInfo;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalView extends BaseView<PersonalContract.Presenter> implements PersonalContract.View {

    @BindView(R.id.iv_back_content)
    ImageView mIvBackContent;
    @BindView(R.id.iv_to_follow)
    ImageView mIvToFollow;
    @BindView(R.id.rv_personal_voices)
    RecyclerView mRvPersonalVoices;
    @BindView(R.id.personal_user_avatar)
    ImageView mPersonalUserAvatar;
    @BindView(R.id.personal_user_name)
    TextView mPersonalUserName;
    @BindView(R.id.tv_follow_number)
    TextView mTvFollowNumber;
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;

    private XingVoiceUser mXingVoiceUser;
    private BasicUserInfo mScanedUser;

    public void setXingVoiceUser(XingVoiceUser xingVoiceUser) {
        mXingVoiceUser = xingVoiceUser;
    }

    private PersonalAdapter mAdapter;

    public PersonalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new PersonalAdapter();
        mRvPersonalVoices.setItemAnimator(new DefaultItemAnimator());
        mRvPersonalVoices.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvPersonalVoices.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.personal_view;
    }

    @OnClick(R.id.iv_back_content)
    public void onMIvBackContentClicked() {
        ((PersonalActivity)getContext()).onBackPressed();
    }

    @OnClick(R.id.iv_to_follow)
    public void onMIvToFollowClicked() {
    }

    @Override
    public void loadData(List<RemoteVoice> voices) {
        if(null == voices){
            return;
        }

        mAdapter.refreshData(voices);

        if(0 == voices.get(0).getIs_focus()){
            mIvToFollow.setVisibility(VISIBLE);
        }

    }

    @Override
    public void loadData(BasicUserInfo userInfo) {
        mScanedUser = userInfo;

        BaseUtil.loadCirclePic(userInfo.getHeadpic()).into(mPersonalUserAvatar);

        mPersonalUserName.setText(userInfo.getNickname());
        mTvFansNum.setText(String.valueOf(userInfo.getFensi()));
        mTvFollowNumber.setText(String.valueOf(userInfo.getGuanzhu()));

        getPresenter().requestUserVoices(mXingVoiceUser);
    }

    public XingVoiceUser getXingVoiceUser() {
        return mXingVoiceUser;
    }
}
