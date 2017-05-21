package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

import java.util.List;

import butterknife.BindView;

public class TextCommentView extends BaseView<TextCommentContract.Presenter> implements TextCommentContract.View {

    @BindView(R.id.iv_com_user_avatar_first)
    ImageView mIvComUserAvatarFirst;
    @BindView(R.id.tv_com_user_name_first)
    TextView mTvComUserNameFirst;
    @BindView(R.id.rl_com_basic_info_first)
    RelativeLayout mRlComBasicInfoFirst;
    @BindView(R.id.tv_com_content_first)
    TextView mTvComContentFirst;
    @BindView(R.id.tv_com_like_num_first)
    TextView mTvComLikeNumFirst;
    @BindView(R.id.iv_com_like_it_first)
    ImageView mIvComLikeItFirst;
    @BindView(R.id.iv_com_user_avatar_second)
    ImageView mIvComUserAvatarSecond;
    @BindView(R.id.tv_com_user_name_second)
    TextView mTvComUserNameSecond;
    @BindView(R.id.rl_com_basic_info_second)
    RelativeLayout mRlComBasicInfoSecond;
    @BindView(R.id.tv_com_content_second)
    TextView mTvComContentSecond;
    @BindView(R.id.tv_com_like_num_second)
    TextView mTvComLikeNumSecond;
    @BindView(R.id.iv_com_like_it_second)
    ImageView mIvComLikeItSecond;
    @BindView(R.id.first)
    RelativeLayout mFirst;
    @BindView(R.id.second)
    RelativeLayout mSecond;

    private RemoteVoice mRemoteVoice;

    public TextCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_text_com_view;
    }

    @Override
    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    @Override
    public void updateData(List<CommentBean> been) {
        CommentBean bean;
        switch (been.size()){
            case 0:
                mFirst.setVisibility(INVISIBLE);
                mSecond.setVisibility(INVISIBLE);
                break;
            case 1:
                bean = been.get(0);
                mTvComContentFirst.setText(bean.getContent());
                mTvComLikeNumFirst.setText(String.valueOf(bean.getZan()));
                mTvComUserNameFirst.setText(bean.getUser());
                BaseUtil.loadCirclePic(bean.getHeadpic()).into(mIvComUserAvatarFirst);
                if(bean.getIs_zan()==1){
                    mIvComLikeItFirst.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
                }
                mSecond.setVisibility(INVISIBLE);
                break;
            default:
                bean = been.get(0);
                mTvComContentFirst.setText(bean.getContent());
                mTvComLikeNumFirst.setText(String.valueOf(bean.getZan()));
                mTvComUserNameFirst.setText(bean.getUser());
                BaseUtil.loadCirclePic(bean.getHeadpic()).into(mIvComUserAvatarFirst);
                if(bean.getIs_zan()==1){
                    mIvComLikeItFirst.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
                }

                bean = been.get(1);
                mTvComContentSecond.setText(bean.getContent());
                mTvComLikeNumSecond.setText(String.valueOf(bean.getZan()));
                mTvComUserNameSecond.setText(bean.getUser());
                BaseUtil.loadCirclePic(bean.getHeadpic()).into(mIvComUserAvatarSecond);
                if(bean.getIs_zan()==1){
                    mIvComLikeItSecond.setImageDrawable(BaseUtil.getDrawable(R.drawable.ic_main_voice_down_like));
                }
        }
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }
}
