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

    //这两个User都是被浏览的用户，前者包含id，昵称，头像三个信息，后者包含昵称头像粉丝关注四个信息
    //后者通过前者获取
    private XingVoiceUser mXingVoiceUser;
    private BasicUserInfo mScanedUser;

    private int mCurIsFollow;

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
        getPresenter().changeFollowState(mXingVoiceUser.getUid(),mCurIsFollow);

        if(1 == mCurIsFollow){
            mCurIsFollow = 0;
            mIvToFollow.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_personal_cancle_follow));
            return;
        }

        mCurIsFollow = 1;
        mIvToFollow.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_personal_to_follow));
    }

    @Override
    public void loadData(List<RemoteVoice> voices) {
        if(null == voices){
            return;
        }

        mAdapter.refreshData(voices);

        mCurIsFollow = 1;
        if(1 == voices.get(0).getIs_focus()){
            mIvToFollow.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_personal_cancle_follow));
            mCurIsFollow = 0;
        }
        mIvToFollow.setVisibility(VISIBLE);

    }

    @Override
    public void loadData(BasicUserInfo userInfo) {
        mScanedUser = userInfo;

        BaseUtil.loadCirclePic(mScanedUser.getHeadpic()).into(mPersonalUserAvatar);

        mPersonalUserName.setText(mScanedUser.getNickname());
        mTvFansNum.setText(String.valueOf(mScanedUser.getFensi()));
        mTvFollowNumber.setText(String.valueOf(mScanedUser.getGuanzhu()));

        getPresenter().requestUserVoices(mXingVoiceUser);
    }

    public XingVoiceUser getXingVoiceUser() {
        return mXingVoiceUser;
    }

    @Override
    public void changeStateSuccess(String info) {
        BaseUtil.showToast(info);
    }
}
