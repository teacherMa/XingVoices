package com.example.xiaomage.xingvoices.feature.main.menu.systemMessage;

import android.content.Context;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.Resp.myVoiceCommentResp.MyVoiceCommentResp;

public class MessageAdapter extends BaseAdapter<MyVoiceCommentResp> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new MessageVH(context, parent);
    }
}
