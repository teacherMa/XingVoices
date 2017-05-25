package com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.event.PlayVoiceComEvent;
import com.example.xiaomage.xingvoices.event.ToLikeComEvent;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class VoiceCommentVH extends BaseViewHolder<CommentBean> {

    @BindView(R.id.iv_com_user_avatar)
    ImageView mIvComUserAvatar;
    @BindView(R.id.tv_com_user_name)
    TextView mTvComUserName;
    @BindView(R.id.tv_voice_com_length)
    TextView mTvVoiceComLength;
    @BindView(R.id.iv_com_content)
    ImageView mIvComContent;
    @BindView(R.id.tv_com_like_num)
    TextView mTvComLikeNum;
    @BindView(R.id.iv_com_like_it)
    ImageView mIvComLikeIt;

    private CommentBean mCommentBean;
    private int mCurLike;
    private int mCurPosition;
    private OnItemClickListener<CommentBean> mCommentBeanOnItemClickListener;

    public VoiceCommentVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_simple_voice_comment_item);
    }

    @Override
    protected void bindData(CommentBean itemValue, int position, OnItemClickListener listener) {

        mCommentBean = itemValue;
        mCurLike = mCommentBean.getIs_zan();
        mCommentBeanOnItemClickListener = listener;
        mCurPosition = position;

        BaseUtil.loadCirclePic(itemValue.getHeadpic()).into(mIvComUserAvatar);
        mTvComUserName.setText(itemValue.getUser());

        if(itemValue.getClength()>0) {
            mTvVoiceComLength.setText(String.valueOf(itemValue.getClength()));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    mIvComContent.getLayoutParams();

            double rate = Math.log(itemValue.getClength() + 1)/8;

            WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);

            layoutParams.width = (int) (metrics.widthPixels * rate);
            mIvComContent.setLayoutParams(layoutParams);
        }

        mTvComLikeNum.setText(String.valueOf(itemValue.getZan()));
        if(mCurLike == 1){
            mIvComLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
        }
    }

    @OnClick({R.id.iv_com_content, R.id.iv_com_like_it})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_com_content:
                mCommentBeanOnItemClickListener.onItemClick(mCommentBean,R.id.iv_com_content,mCurPosition);
                break;
            case R.id.iv_com_like_it:
                if (mCurLike == 1) {
                    BaseUtil.showToast(BaseUtil.getString(R.string.main_like_it_before));
                    return;
                }
                mTvComLikeNum.setText(String.valueOf(mCommentBean.getZan()+1));
                mIvComLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
                mCommentBeanOnItemClickListener.onItemClick(mCommentBean,R.id.iv_com_like_it,mCurPosition);
                mCurLike = 1;
                break;
        }
    }
}
