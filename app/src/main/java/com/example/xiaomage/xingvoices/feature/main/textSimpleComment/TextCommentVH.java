package com.example.xiaomage.xingvoices.feature.main.textSimpleComment;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TextCommentVH extends BaseViewHolder<CommentBean> {

    @BindView(R.id.iv_com_user_avatar)
    ImageView mIvComUserAvatar;
    @BindView(R.id.tv_com_user_name)
    TextView mTvComUserName;
    @BindView(R.id.rl_com_basic_info)
    RelativeLayout mRlComBasicInfo;
    @BindView(R.id.tv_com_content)
    TextView mTvComContent;
    @BindView(R.id.tv_com_like_num)
    TextView mTvComLikeNum;
    @BindView(R.id.iv_com_like_it)
    ImageView mIvComLikeIt;

    private CommentBean mCommentBean;
    private OnItemClickListener<CommentBean> mCommentBeanOnItemClickListener;
    private int mCurLike;
    private int mCurPosition;

    public TextCommentVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_simple_text_comment_item);
    }

    @Override
    protected void bindData(CommentBean itemValue, int position, OnItemClickListener listener) {

        mCommentBean = itemValue;
        mCurLike = mCommentBean.getIs_zan();
        mCommentBeanOnItemClickListener = listener;
        mCurPosition = position;

        BaseUtil.loadCirclePic(itemValue.getHeadpic()).into(mIvComUserAvatar);
        mTvComUserName.setText(itemValue.getUser());
        mTvComContent.setText(itemValue.getContent());
        mTvComLikeNum.setText(String.valueOf(itemValue.getZan()));
        if(mCurLike == 1){
            mIvComLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
        }
    }

    @OnClick(R.id.iv_com_like_it)
    public void onViewClicked() {
        if (mCurLike == 1) {
            BaseUtil.showToast(BaseUtil.getString(R.string.main_like_it_before));
            return;
        }
        mTvComLikeNum.setText(String.valueOf(mCommentBean.getZan()+1));
        mIvComLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
        mCommentBeanOnItemClickListener.onItemClick(mCommentBean,R.id.iv_com_like_it, mCurPosition);
        mCurLike = 1;
    }
}
