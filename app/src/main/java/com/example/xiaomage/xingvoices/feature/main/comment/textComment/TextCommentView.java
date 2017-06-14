package com.example.xiaomage.xingvoices.feature.main.comment.textComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.event.EmptyEvent;
import com.example.xiaomage.xingvoices.event.ToLikeComEvent;
import com.example.xiaomage.xingvoices.framework.BaseBusView;
import com.example.xiaomage.xingvoices.model.bean.CommentBean.CommentBean;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.utils.BaseUtil;
import com.example.xiaomage.xingvoices.utils.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class TextCommentView extends BaseBusView<TextCommentContract.Presenter> implements TextCommentContract.View {

    @BindView(R.id.rv_text_com)
    RecyclerView mRvTextCom;
    @BindView(R.id.load_bar)
    ProgressBar mLoadBar;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;

    private TextCommentAdapter mAdapter;
    private RemoteVoice mRemoteVoice;
    private int mModel;
    private int mCurPage = 1;
    private boolean mIsLoadingMore;

    public TextCommentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent event) {
        if (event == null) {
            return;
        }
        if (event instanceof ToLikeComEvent) {
            ToLikeComEvent likeComEvent = (ToLikeComEvent) event;
            if (!likeComEvent.getTag().equals(Constants.ViewHolderTag.TextCommentVH)) {
                return;
            }
            if (likeComEvent.getId() == null) {
                return;
            }
            toLikeCom(likeComEvent.getId());
        }
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mAdapter = new TextCommentAdapter();
        mRvTextCom.setItemAnimator(new DefaultItemAnimator());
        mRvTextCom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvTextCom.setAdapter(mAdapter);

        mRvTextCom.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int enable = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                mSrlRefresh.setEnabled(enable == 0);
                if (mIsLoadingMore) {
                    super.onScrolled(recyclerView, dx, dy);
                    return;
                }
                if (!recyclerView.canScrollVertically(1)) {
                    mCurPage++;
                    mLoadBar.setVisibility(VISIBLE);
                    mIsLoadingMore = true;
                    getPresenter().requestComment(mRemoteVoice,mCurPage*10);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().requestComment(mRemoteVoice,mCurPage);
                mIsLoadingMore = false;
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_text_com_view;
    }

    @Override
    public RemoteVoice getRemoteVoice() {
        return mRemoteVoice;
    }

    @Override
    public void updateData(List<CommentBean> been) {
        if (null == mRvTextCom.getAdapter()) {
            TextCommentAdapter adapter = new TextCommentAdapter();
            mRvTextCom.setAdapter(adapter);
        }
        ((TextCommentAdapter) mRvTextCom.getAdapter()).refreshData(been);

        mSrlRefresh.setRefreshing(false);
        mLoadBar.setVisibility(GONE);
    }

    @Override
    public void likeSuccess(String info) {
        BaseUtil.showToast(info + BaseUtil.getString(R.string.main_like_success));
    }

    public void setRemoteVoice(RemoteVoice remoteVoice) {
        mRemoteVoice = remoteVoice;
    }

    public void setModel(int model) {
        mModel = model;
    }

    private void toLikeCom(String cId) {
        getPresenter().likeIt(cId);
    }

}
