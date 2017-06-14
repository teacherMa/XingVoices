package com.example.xiaomage.xingvoices.feature.main.menu.systemMessage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class MessageActivity extends BaseActivity<MessagePresenter> {
    @BindView(R.id.message_view)
    MessageView mMessageView;

    public static Intent getIntent(Context context){
        return new Intent(context,MessageActivity.class);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter(
                Injection.provideMainRepository(),
                mMessageView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_message_act;
    }


}
