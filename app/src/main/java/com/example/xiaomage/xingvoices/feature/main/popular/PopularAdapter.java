package com.example.xiaomage.xingvoices.feature.main.popular;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

public class PopularAdapter extends BaseAdapter<RemoteVoice> {

    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new PopularVH(context, parent).setId();
    }
}
