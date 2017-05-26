package com.example.xiaomage.xingvoices.feature.main.menu.systemMessage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.framework.BaseView;
import com.example.xiaomage.xingvoices.model.bean.myVoiceCommentResp.MyVoiceCommentResp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageView extends BaseView<MessageContract.Presenter> implements MessageContract.View {

    @BindView(R.id.iv_back_main)
    ImageView mIvBackMain;
    @BindView(R.id.rv_all_message)
    RecyclerView mRvAllMessage;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.load_bar)
    ProgressBar mLoadBar;

    private MessageAdapter mAdapter;
    private int mCurPageNum = 1;
    private boolean mIsLoadingMore = false;

    public MessageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mAdapter = new MessageAdapter();
        mRvAllMessage.setItemAnimator(new DefaultItemAnimator());
        mRvAllMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvAllMessage.setAdapter(mAdapter);

        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().requestComment(mCurPageNum);
                mIsLoadingMore = false;
            }
        });

        mRvAllMessage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {
                    mCurPageNum++;
                    mLoadBar.setVisibility(VISIBLE);
                    getPresenter().requestComment(mCurPageNum);
                    mIsLoadingMore = true;
                }
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int enable = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                mSrlRefresh.setEnabled(enable == 0);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_message_view;
    }

    @OnClick(R.id.iv_back_main)
    public void onViewClicked() {
        ((MessageActivity) getContext()).onBackPressed();
    }

    @Override
    public void requestSuccess(List<MyVoiceCommentResp> resp) {
        int origin = 0;
        if(null != mAdapter.getValueList()) {
            origin = mAdapter.getValueList().size();
        }
        mSrlRefresh.setRefreshing(false);
        mLoadBar.setVisibility(GONE);
        if(null != mRvAllMessage.getAdapter()) {
            ((MessageAdapter) mRvAllMessage.getAdapter()).refreshData(resp);
        }
        if(mIsLoadingMore){
            mRvAllMessage.scrollToPosition(origin - 1);
        }
    }
}
