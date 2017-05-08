package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.custom.view.CircleImageView;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice;

import butterknife.BindView;
import butterknife.OnClick;

public class PopularVH extends BaseViewHolder<RemoteVoice> {

    @BindView(R.id.civ_user_avatar)
    CircleImageView mCivUserAvatar;
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
    @BindView(R.id.rv_text_com_list)
    RecyclerView mRvTextComList;
    @BindView(R.id.rv_voice_com_list)
    RecyclerView mRvVoiceComList;
    @BindView(R.id.tv_more_com)
    TextView mTvMoreCom;

    public PopularVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_popular_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {

    }

    @OnClick(R.id.civ_user_avatar)
    public void onMCivUserAvatarClicked() {
    }

    @OnClick(R.id.iv_follow)
    public void onMIvFollowClicked() {
    }

    @OnClick(R.id.iv_more)
    public void onMIvMoreClicked() {
    }

    @OnClick(R.id.tv_text_com)
    public void onMTvTextComClicked() {
    }

    @OnClick(R.id.tv_voice_com)
    public void onMTvVoiceComClicked() {
    }

    @OnClick(R.id.tv_more_com)
    public void onMTvMoreComClicked() {
    }
}
