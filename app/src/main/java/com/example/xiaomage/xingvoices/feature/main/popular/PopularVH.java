package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.model.bean.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.R;

import butterknife.BindView;

public class PopularVH extends BaseViewHolder<RemoteVoice> {

    public PopularVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_popular_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {

    }
}
