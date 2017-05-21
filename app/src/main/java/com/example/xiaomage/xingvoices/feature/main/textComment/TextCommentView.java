package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.feature.main.popular.PopularAdapter;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import java.util.List;

import butterknife.BindView;

public class TextCommentView extends BaseView<TextCommentContract.Presenter> implements TextCommentContract.View {

    public static final String TAG = "TextCommentView";

    @BindView(R.id.rv_text_com)
    RecyclerView mRvTextCom;

    private int mModel;
    private RemoteVoice mRemoteVoice;

    private class TextCommentHolder extends RecyclerView.ViewHolder {

        private CommentBean mCommentBean;

        private TextView mTvName;
        private TextView mTvComContent;
        private TextView mTvLikeNum;
        private ImageView mIvLikeIt;
        private ImageView mIvAvatar;

        public TextCommentHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_com_user_name);
            mTvComContent = (TextView) itemView.findViewById(R.id.tv_com_content);
            mTvLikeNum = (TextView) itemView.findViewById(R.id.tv_com_like_num);
            mIvAvatar = (ImageView) itemView.findViewById(R.id.iv_com_user_avatar);
            mIvLikeIt = (ImageView) itemView.findViewById(R.id.iv_com_like_it);
        }

        public void bindVoice(CommentBean textCommentBean) {
            Log.e(TAG, this.toString() + " bind data" );

            mCommentBean = textCommentBean;

            mTvName.setText(mCommentBean.getUser());
            mTvComContent.setText(mCommentBean.getContent());
            mTvLikeNum.setText(String.valueOf(mCommentBean.getZan()));
            BaseUtil.loadCirclePic(mCommentBean.getHeadpic()).into(mIvAvatar);
            if (mCommentBean.getIs_zan() == 1) {
                mIvLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
            }
        }

    }

    private class TextCommentAdapter extends RecyclerView.Adapter<TextCommentHolder> {

        private List<CommentBean> mBeen;

        public TextCommentAdapter(List<CommentBean> been) {
            mBeen = been;
        }

        @Override
        public TextCommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (Constants.CommentItemType.SIMPLE == mModel) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.
                        main_text_comment_simple_item, parent,false);
                return new TextCommentHolder(view);
            }
            view = LayoutInflater.from(getContext()).inflate(R.layout.
                    text_comment_item, parent, false);
            return new TextCommentHolder(view);
        }

        @Override
        public void onBindViewHolder(TextCommentHolder holder, int position) {
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

    public TextCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mRvTextCom.setItemAnimator(new DefaultItemAnimator());
        mRvTextCom.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_text_com_view;
    }

    public int getModel() {
        return mModel;
    }

    public void setModel(int model) {
        mModel = model;
    }

    @Override
    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    @Override
    public void updateData(List<CommentBean> been) {
        Log.e(TAG, this.toString() + " bind data, data size = "+been.size() +"recyclerView :"+mRvTextCom.getWidth() +" "+mRvTextCom.getHeight());
        if(null == mRvTextCom.getAdapter()){
            TextCommentAdapter adapter = new TextCommentAdapter(been);
            mRvTextCom.setAdapter(adapter);
            return;
        }
        ((TextCommentAdapter)mRvTextCom.getAdapter()).refreshData(been);
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }
}
