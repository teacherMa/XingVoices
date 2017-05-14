package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
import com.example.xiaomage.xingvoices.custom.view.CircleImageView;
import com.example.xiaomage.xingvoices.custom.view.bottomMenu.BottomMenu;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.UserBean;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.xiaomage.xingvoices.utils.Constants.BottomMenuItem.*;
import static com.example.xiaomage.xingvoices.utils.Constants.MainPopularItem.*;

public class PopularVH extends BaseViewHolder<RemoteVoice> implements OnBottomMenuItemClickListener {

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

    private boolean mIsUser;
    private OnItemClickListener<RemoteVoice> mOnItemClickListener;
    private RemoteVoice mRemoteVoice;

    public PopularVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_popular_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {
        mIsUser = position % 2 == 0;
        mOnItemClickListener = listener;
        mRemoteVoice = itemValue;
    }

    @OnClick(R.id.civ_user_avatar)
    public void onMCivUserAvatarClicked() {
        UserBean userBean = new UserBean();
        userBean.setUser(mIsUser);
        Intent intent = PersonalActivity.getNewIntent(userBean, getContext());
        getContext().startActivity(intent);
    }

    @OnClick(R.id.iv_follow)
    public void onMIvFollowClicked() {
        mOnItemClickListener.onItemClick(mRemoteVoice,R.id.iv_follow, FOLLOW);
    }

    @OnClick(R.id.iv_more)
    public void onMIvMoreClicked() {
        BottomMenu window = new BottomMenu(getContext());
        View root = LayoutInflater.from(getContext()).inflate(R.layout.main_view,null);
        window.showAtLocation(root, Gravity.BOTTOM,0,0);
        window.setOnBottomMenuItemClickListener(this);
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

    @Override
    public void onItemClick(int position) {
        switch (position){
            case COMMENT:
                // TODO: 2017/5/14
                break;
            case COLLECTION:
                mOnItemClickListener.onItemClick(mRemoteVoice,R.id.tv_collection, COLLECTION);
                break;
            case SHARE:
                // TODO: 2017/5/14
                break;
            case LOOK_UP_PIC:
                // TODO: 2017/5/14
                break;
            case ADD_TO_BLACK_LIST:
                mOnItemClickListener.onItemClick(mRemoteVoice,R.id.tv_add_to_blacklist,ADD_TO_BLACK_LIST);
        }
    }
}
