package com.example.xiaomage.xingvoices.feature.main.collection;

import android.content.Context;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.api.OnItemClickListener;
import com.example.xiaomage.xingvoices.R;

public class CollectionVH extends BaseViewHolder<RemoteVoice> {

    public CollectionVH(Context context, ViewGroup root) {
        super(context, root, R.layout.main_collection_item);
    }

    @Override
    protected void bindData(RemoteVoice itemValue, int position, OnItemClickListener listener) {

    }
}
