package com.example.xiaomage.xingvoices.feature.main.menu.systemMessage;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.CommentsBean;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.MyVoiceCommentResp;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import butterknife.BindView;

public class MessageVH extends BaseViewHolder<MyVoiceCommentResp> {

    @BindView(R.id.tv_com_voice_title)
    TextView mTvComVoiceTitle;
    @BindView(R.id.rv_com)
    RecyclerView mRvCom;

    public MessageVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_message_item);
    }

    protected class ComItemVH extends BaseViewHolder<CommentsBean> {

        @BindView(R.id.iv_com_user_avatar)
        ImageView mIvComUserAvatar;
        @BindView(R.id.tv_com_user_name)
        TextView mTvComUserName;
        @BindView(R.id.tv_com_content)
        TextView mTvComContent;

        public ComItemVH(Context context, ViewGroup root) {
            super(context, root, R.layout.main_message_com_item);
        }

        @Override
        protected void bindData(CommentsBean itemValue, int position, OnItemClickListener listener) {
            BaseUtil.loadCirclePic(itemValue.getHeadpic()).into(mIvComUserAvatar);
            mTvComUserName.setText(itemValue.getUser());
            mTvComContent.setText(itemValue.getContent());
        }
    }

    protected class ComItemAdapter extends BaseAdapter<CommentsBean>{

        @Override
        protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
            return new ComItemVH(context,parent);
        }
    }

    @Override
    protected void bindData(MyVoiceCommentResp itemValue, int position, OnItemClickListener listener) {

        mTvComVoiceTitle.setText(itemValue.getTitle());

        ComItemAdapter adapter = new ComItemAdapter();
        mRvCom.setItemAnimator(new DefaultItemAnimator());
        mRvCom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCom.setAdapter(adapter);

        adapter.refreshData(itemValue.getComments());
    }
}
