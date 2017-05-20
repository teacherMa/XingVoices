package com.example.xiaomage.xingvoices.feature.record.publish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.model.bean.LocalVoice.LocalVoice;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class PublishActivity extends BaseActivity<PublishPresenter> {

    private static final String ARG_VOICE = "local voice bean";

    public static Intent getNewIntent(Context context, LocalVoice localVoice){

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_VOICE,localVoice);

        Intent intent = new Intent(context,PublishActivity.class);
        intent.putExtras(bundle);

        return intent;
    }

    @BindView(R.id.record_publish_view)
    PublishView mRecordPublishView;

    LocalVoice mLocalVoice;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected PublishPresenter createPresenter() {

        mLocalVoice=(LocalVoice) getIntent().getSerializableExtra(ARG_VOICE);
        mRecordPublishView.setLocalVoice(mLocalVoice);

        return new PublishPresenter(
                Injection.provideRecordRepository(),
                mRecordPublishView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.record_publish_frag;
    }

}
