package com.example.xiaomage.xingvoices.feature.personal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;

import butterknife.BindView;

public class PersonalView extends BaseView<PersonalContract.Presenter> implements PersonalContract.View {

    @BindView(R.id.rv_personal_voices)
    RecyclerView mRvPersonalVoices;

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
}
