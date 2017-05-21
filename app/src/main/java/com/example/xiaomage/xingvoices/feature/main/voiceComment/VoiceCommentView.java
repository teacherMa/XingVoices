package com.example.xiaomage.xingvoices.feature.main.voiceComment;

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

import java.util.List;

import butterknife.BindView;

public class VoiceCommentView extends BaseView<VoiceCommentContract.Presenter> implements VoiceCommentContract.View {

    @BindView(R.id.iv_com_user_avatar_first)
    ImageView mIvComUserAvatarFirst;
    @BindView(R.id.tv_com_user_name_first)
    TextView mTvComUserNameFirst;
    @BindView(R.id.tv_voice_com_length_first)
    TextView mTvVoiceComLengthFirst;
    @BindView(R.id.iv_com_content_first)
    ImageView mIvComContentFirst;
    @BindView(R.id.tv_com_like_num_first)
    TextView mTvComLikeNumFirst;
    @BindView(R.id.iv_com_like_it_first)
    ImageView mIvComLikeItFirst;
    @BindView(R.id.first)
    RelativeLayout mFirst;
    @BindView(R.id.iv_com_user_avatar_second)
    ImageView mIvComUserAvatarSecond;
    @BindView(R.id.tv_com_user_name_second)
    TextView mTvComUserNameSecond;
    @BindView(R.id.tv_voice_com_length_second)
    TextView mTvVoiceComLengthSecond;
    @BindView(R.id.iv_com_content_second)
    ImageView mIvComContentSecond;
    @BindView(R.id.tv_com_like_num_second)
    TextView mTvComLikeNumSecond;
    @BindView(R.id.iv_com_like_it_second)
    ImageView mIvComLikeItSecond;
    @BindView(R.id.second)
    RelativeLayout mSecond;
    private RemoteVoice mRemoteVoice;

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
