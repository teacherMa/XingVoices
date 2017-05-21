package com.example.xiaomage.xingvoices.feature.main.voiceComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.main.textComment.TextCommentView;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

import butterknife.BindView;

public class VoiceCommentView extends BaseView<VoiceCommentContract.Presenter> implements VoiceCommentContract.View {

    @BindView(R.id.rv_voice_com)
    RecyclerView mRvVoiceCom;

    private int mModel;
    private RemoteVoice mRemoteVoice;

    private class VoiceCommentHolder extends RecyclerView.ViewHolder {

        private CommentBean mCommentBean;
        private TextView mTvName;
        private TextView mTvLikeNum;
        private ImageView mTvComContent;
        private ImageView mIvLikeIt;
        private ImageView mIvAvatar;

        public VoiceCommentHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_com_user_name);
            mTvComContent = (ImageView) itemView.findViewById(R.id.iv_com_content);
            mTvLikeNum = (TextView) itemView.findViewById(R.id.tv_com_like_num);
            mIvAvatar = (ImageView) itemView.findViewById(R.id.iv_com_user_avatar);
            mIvLikeIt = (ImageView) itemView.findViewById(R.id.iv_com_like_it);
        }

        public void bindVoice(CommentBean textCommentBean) {
            mTvName.setText(textCommentBean.getUser());
            mTvLikeNum.setText(String.valueOf(textCommentBean.getZan()));
            BaseUtil.loadCirclePic(textCommentBean.getHeadpic()).into(mIvAvatar);
            if (textCommentBean.getIs_zan() == 1) {
                mIvLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
            }
        }

    }

    private class VoiceCommentAdapter extends RecyclerView.Adapter<VoiceCommentHolder> {

        private List<CommentBean> mBeen;

        public VoiceCommentAdapter(List<CommentBean> been) {
            mBeen = been;
        }

        @Override
        public VoiceCommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (Constants.CommentItemType.SIMPLE == mModel) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.
                        main_text_comment_simple_item, parent, false);
                return new VoiceCommentHolder(view);
            }
            view = LayoutInflater.from(getContext()).inflate(R.layout.
                    text_comment_item, parent, false);
            return new VoiceCommentHolder(view);
        }

        @Override
        public void onBindViewHolder(VoiceCommentHolder holder, int position) {
            holder.bindVoice(mBeen.get(position));
        }

        @Override
        public int getItemCount() {
            if (mModel == Constants.CommentItemType.SIMPLE) {
                return 2;
            }
            return mBeen.size();
        }

        public void refreshData(List<CommentBean>been){
            mBeen = been;
            notifyDataSetChanged();
        }
    }

    public VoiceCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_voice_com_view;
    }

    public int getModel() {
        return mModel;
    }

    public void setModel(int model) {
        mModel = model;
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    @Override
    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    @Override
    public void updateData(List<CommentBean> been) {

    }
}
