package com.example.xiaomage.xingvoices.framework;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Reload some functions .
 * It extends FrameLayout , because FrameLayout is simplest ViewGroup .
 * <p>
 */

public abstract class BaseView<P extends BasePresenterApi> extends FrameLayout implements BaseViewApi<P> {

    private boolean mActive;
    private P mPresenter;

    public BaseView(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        View view = inflate(context, getLayoutResId(), this);
        ButterKnife.bind(this, view);
        initView(context, attrs, defStyleAttr);
    }

    public P getPresenter() {
        if (null != mPresenter) {
            return mPresenter;
        }
        throw new NullPointerException();
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
        mPresenter.start();
    }

    /**
     * Let the child init .
     */
    protected abstract void initView(Context context, AttributeSet attrs, int defStyleAttr);

    /**
     * set resource id .
     *
     * @return layout id of current view
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
    }
}
