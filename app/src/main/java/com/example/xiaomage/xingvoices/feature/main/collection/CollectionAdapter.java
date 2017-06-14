package com.example.xiaomage.xingvoices.feature.main.collection;

import android.content.Context;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;

public class CollectionAdapter extends BaseAdapter<RemoteVoice> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new CollectionVH(context, parent).setId();
    }
}
