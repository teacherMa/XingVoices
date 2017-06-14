package com.example.xiaomage.xingvoices.feature.main.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.event.ShowNewMessage;
import com.example.xiaomage.xingvoices.feature.main.comment.textComment.TextCommentFragment;
import com.example.xiaomage.xingvoices.feature.main.comment.voiceComment.VoiceCommentFragment;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentView extends BaseView<CommentContract.Presenter> implements CommentContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_text_com)
    TextView mTvTextCom;
    @BindView(R.id.tv_voice_com)
    TextView mTvVoiceCom;
    @BindView(R.id.vp_com)
    ViewPager mVpCom;

    private RemoteVoice mRemoteVoice;
    private List<Fragment> mFragments;

    public CommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.com_view;
    }

    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
        prepareFragments();
        initViewPager();
    }

    private void prepareFragments() {
        mFragments = new ArrayList<>();

        TextCommentFragment textCommentFragment = new TextCommentFragment();
        textCommentFragment.setRemoteVoice(mRemoteVoice);
        textCommentFragment.setModel(Constants.CommentItemType.TOTAL);
        mFragments.add(textCommentFragment);

        VoiceCommentFragment voiceCommentFragment = new VoiceCommentFragment();
        voiceCommentFragment.setRemoteVoice(mRemoteVoice);
        voiceCommentFragment.setModel(Constants.CommentItemType.TOTAL);
        mFragments.add(voiceCommentFragment);
    }

    private void initViewPager(){
        FragmentManager fragmentManager = ((CommentActivity) getContext()).getSupportFragmentManager();

        mVpCom.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mVpCom.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        break;
                    case 1:
                        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
                        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mVpCom.setCurrentItem(0);
        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
    }

    @OnClick(R.id.iv_back)
    public void onMIvBackClicked() {
        ((CommentActivity)getContext()).onBackPressed();
        EventBus.getDefault().post(new ShowNewMessage(false));
    }

    @OnClick(R.id.tv_text_com)
    public void onMTvTextComClicked() {
        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
        mVpCom.setCurrentItem(0);
    }

    @OnClick(R.id.tv_voice_com)
    public void onMTvVoiceComClicked() {
        mTvVoiceCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextSelected));
        mTvTextCom.setTextColor(BaseUtil.getColorInt(R.color.colorTextUnselected));
        mVpCom.setCurrentItem(1);
    }
}
