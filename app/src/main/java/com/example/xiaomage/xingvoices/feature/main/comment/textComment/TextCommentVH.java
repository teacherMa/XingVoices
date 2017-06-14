package com.example.xiaomage.xingvoices.feature.main.comment.textComment;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.event.ToLikeComEvent;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class TextCommentVH extends BaseViewHolder<CommentBean> {

    @BindView(R.id.iv_com_user_avatar)
    ImageView mIvComUserAvatar;
    @BindView(R.id.tv_com_user_name)
    TextView mTvComUserName;
    @BindView(R.id.tv_com_content)
    TextView mTvComContent;
    @BindView(R.id.tv_com_like_num)
    TextView mTvComLikeNum;
    @BindView(R.id.iv_com_like_it)
    ImageView mIvComLikeIt;

    private CommentBean mCommentBean;
    private int mCurLike;

    public TextCommentVH(Context context, ViewGroup root) {
        super(context, root, R.layout.text_comment_item);
    }

    @Override
    protected void bindData(CommentBean itemValue, int position, OnItemClickListener listener) {

        mCommentBean = itemValue;
        mCurLike = mCommentBean.getIs_zan();

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
        EventBus.getDefault().post(new ToLikeComEvent(mCommentBean.getCid(),
                Constants.ViewHolderTag.TextCommentVH));
        mCurLike = 1;
    }
}
