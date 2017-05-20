package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.xiaomage.xingvoices.custom.view.bottomMenu.BottomMenu;
import com.example.xiaomage.xingvoices.feature.personal.PersonalActivity;
import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.TextCommentBean;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.VoiceCommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.UserBean;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

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
    @BindView(R.id.rv_text_com_list)
    RecyclerView mRvTextComList;
    @BindView(R.id.rv_voice_com_list)
    RecyclerView mRvVoiceComList;
    @BindView(R.id.tv_more_com)
    TextView mTvMoreCom;
    @BindView(R.id.tv_voice_name)
    TextView mTvVoiceName;
    @BindView(R.id.tv_voice_length)
    TextView mTvVoiceLength;

    private boolean mIsUser;
    private OnItemClickListener<RemoteVoice> mOnItemClickListener;
    private RemoteVoice mRemoteVoice;

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

        initRv();

    }

    @OnClick(R.id.civ_user_avatar)
    public void onMCivUserAvatarClicked() {
        UserBean userBean = new UserBean();
        Intent intent = PersonalActivity.getNewIntent(userBean, getContext());
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

    }

    @OnClick(R.id.tv_voice_com)
    public void onMTvVoiceComClicked() {
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

    private void initRv() {

        mRvTextComList.setItemAnimator(new DefaultItemAnimator());
        mRvTextComList.setLayoutManager(new LinearLayoutManager(getContext()));

        TextListAdapter textListAdapter = new TextListAdapter();

        mRvTextComList.setAdapter(textListAdapter);

        mRvVoiceComList.setItemAnimator(new DefaultItemAnimator());
        mRvVoiceComList.setLayoutManager(new LinearLayoutManager(getContext()));

        VoiceListAdapter voiceListAdapter = new VoiceListAdapter();

        mRvVoiceComList.setAdapter(voiceListAdapter);
    }

    private class TextListHolder extends BaseViewHolder<TextCommentBean> {

        @BindView(R.id.civ_com_user_avatar)
        ImageView mCivComUserAvatar;
        @BindView(R.id.tv_com_user_name)
        TextView mTvComUserName;
        @BindView(R.id.tv_com_content)
        TextView mTvComContent;
        @BindView(R.id.tv_com_like_num)
        TextView mTvComLikeNum;
        @BindView(R.id.iv_com_like_it)
        ImageView mIvComLikeIt;

        public TextListHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.main_text_comment_simple_item);
        }

        @Override
        protected void bindData(TextCommentBean itemValue, int position, OnItemClickListener listener) {

        }

        @OnClick(R.id.iv_com_like_it)
        public void onViewClicked() {
        }
    }

    private class TextListAdapter extends BaseAdapter<TextCommentBean> {

        @Override
        protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
            return new TextListHolder(context, parent);
        }
    }

    private class VoiceListHolder extends BaseViewHolder<VoiceCommentBean> {

        @BindView(R.id.civ_com_user_avatar)
        ImageView mCivComUserAvatar;
        @BindView(R.id.tv_com_user_name)
        TextView mTvComUserName;
        @BindView(R.id.tv_voice_com_length)
        TextView mTvVoiceComLength;
        @BindView(R.id.tv_com_content)
        ImageView mTvComContent;
        @BindView(R.id.tv_com_like_num)
        TextView mTvComLikeNum;
        @BindView(R.id.iv_com_like_it)
        ImageView mIvComLikeIt;

        public VoiceListHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.main_voice_comment_simple_item);
        }

        @Override
        protected void bindData(VoiceCommentBean itemValue, int position, OnItemClickListener listener) {

        }

        @OnClick(R.id.iv_com_like_it)
        public void onViewClicked() {
        }
    }

    private class VoiceListAdapter extends BaseAdapter<VoiceCommentBean> {

        @Override
        protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
            return new VoiceListHolder(context, parent);
        }
    }
}
