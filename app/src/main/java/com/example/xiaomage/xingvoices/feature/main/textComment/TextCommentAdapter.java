package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.content.Context;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;

public class TextCommentAdapter extends BaseAdapter<CommentBean> {
    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new TextCommentVH(context, parent);
    }

    @Override
    public int getItemCount() {
        if(null == getValueList()){
            return 0;
        }
        int size = getValueList().size();
        if(size>2){
            return 2;
        }
        return size;
    }
}
