package com.example.xiaomage.xingvoices.feature.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseActivity;
import com.example.xiaomage.xingvoices.utils.Injection;

import butterknife.BindView;

public class RecordActivity extends BaseActivity<RecordPresenter> {

    public static Intent getNewIntent(Context context){
        return new Intent(context,RecordActivity.class);
    }

    @BindView(R.id.record_view)
    RecordView mRecordView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected RecordPresenter createPresenter() {
        return new RecordPresenter(
                Injection.provideRecordRepository(),
                mRecordView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.record_act;
    }

}
