package com.example.xiaomage.xingvoices.feature.main.voiceComment;

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
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

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

    public VoiceCommentVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_voice_comment_simple_item);
    }

    @Override
    protected void bindData(CommentBean itemValue, int position, OnItemClickListener listener) {

        mCommentBean = itemValue;

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
        if(itemValue.getIs_zan() == 1){
            mIvComLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
        }
    }

    @OnClick({R.id.iv_com_content, R.id.iv_com_like_it})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_com_content:
                break;
            case R.id.iv_com_like_it:
                mTvComLikeNum.setText(String.valueOf(mCommentBean.getZan()+1));
                mIvComLikeIt.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
                break;
        }
    }
}
