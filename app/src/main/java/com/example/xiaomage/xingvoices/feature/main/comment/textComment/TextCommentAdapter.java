package com.example.xiaomage.xingvoices.feature.main.comment.textComment;

import android.content.Context;
import android.view.ViewGroup;

import com.example.xiaomage.xingvoices.framework.BaseAdapter;
import com.example.xiaomage.xingvoices.framework.BaseViewHolder;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.utils.Constants;

public class TextCommentAdapter extends BaseAdapter<CommentBean> {

    @Override
    protected BaseViewHolder createViewHolder(Context context, ViewGroup parent) {
        return new TextCommentVH(context, parent);
    }

}
