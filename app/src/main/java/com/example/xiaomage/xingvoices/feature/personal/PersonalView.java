package com.example.xiaomage.xingvoices.feature.personal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalView extends BaseView<PersonalContract.Presenter> implements PersonalContract.View {

    @BindView(R.id.iv_back_content)
    ImageView mIvBackContent;
    @BindView(R.id.iv_to_follow)
    ImageView mIvToFollow;
    @BindView(R.id.rv_personal_voices)
    RecyclerView mRvPersonalVoices;

    private XingVoiceUser mXingVoiceUser;

    public void setXingVoiceUser(XingVoiceUser xingVoiceUser) {
        mXingVoiceUser = xingVoiceUser;
    }

    private PersonalAdapter mAdapter;

    public PersonalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new PersonalAdapter();
        mRvPersonalVoices.setItemAnimator(new DefaultItemAnimator());
        mRvPersonalVoices.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvPersonalVoices.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.personal_view;
    }

    @OnClick(R.id.iv_back_content)
    public void onMIvBackContentClicked() {
        NavUtils.navigateUpFromSameTask((PersonalActivity)getContext());
    }

    @OnClick(R.id.iv_to_follow)
    public void onMIvToFollowClicked() {
    }

    @Override
    public void loadData(List<RemoteVoice> voices) {
        mAdapter.refreshData(voices);
    }

    public XingVoiceUser getXingVoiceUser() {
        return mXingVoiceUser;
    }
}
