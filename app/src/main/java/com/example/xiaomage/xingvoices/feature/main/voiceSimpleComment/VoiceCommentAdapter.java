package com.example.xiaomage.xingvoices.feature.main.voiceSimpleComment;

import android.content.Context;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.utils.Constants;

public class VoiceCommentAdapter extends BaseAdapter<CommentBean> {
    private int mModel = Constants.CommentItemType.SIMPLE;

    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new VoiceCommentVH(context, parent);
    }

    @Override
    public int getItemCount() {
        if(null == getValueList()){
            return 0;
        }
        if(Constants.CommentItemType.SIMPLE == mModel) {
            int size = getValueList().size();
            if (size > 2) {
                return 2;
            }
            return size;
        }
        return super.getItemCount();
    }

    public void setModel(int model) {
        mModel = model;
    }
}
