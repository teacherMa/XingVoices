package com.example.xiaomage.xingvoices.event;

import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;

/**
 * Created by xiaomage on 2017/5/25.
 */

public class PlayVoiceComEvent extends EmptyEvent {
    private String mTag;
    private CommentBean mCommentBean;

    public void setTag(String tag) {
        mTag = tag;
    }

    public void setCommentBean(CommentBean commentBean) {
        mCommentBean = commentBean;
    }

    public String getTag() {

        return mTag;
    }

    public CommentBean getCommentBean() {
        return mCommentBean;
    }

    public PlayVoiceComEvent(String tag, CommentBean commentBean) {

        mTag = tag;
        mCommentBean = commentBean;
    }
}
