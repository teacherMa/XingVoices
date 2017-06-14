package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.utils.BaseUtil;

/**
 * Created by xiaomage on 2017/5/3.
 * A custom view group that implement slide menu
 */

public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private LinearLayout mMenu;
    private RelativeLayout mContent;

    private int mScreenWidth;
    private int mMenuRightPadding = 50;
    private int mMenuWidth;

    private boolean mIsOnce = false;
    private boolean mIsOpen = false;
    private boolean mDrawerType = false;

    public SlidingMenu(Context context) {
        super(context);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu, defStyleAttr, 0);

        int n = typedArray.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                    mMenuRightPadding, context.getResources().getDisplayMetrics()));
                    break;
                case R.styleable.SlidingMenu_drawerType:
                    mDrawerType = typedArray.getBoolean(attr, false);
                    break;
                default:
                    break;
            }
        }

        typedArray.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!mIsOnce) {
            mIsOnce = true;
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (LinearLayout) mWapper.getChildAt(0);
            mContent = (RelativeLayout) mWapper.getChildAt(1);

            mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mMenuWidth = mMenu.getLayoutParams().width;

            mContent.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX > mScreenWidth / 2) {
                    closeMenu();
                } else {
                    openMenu();
                }
                if(ev.getX()>mMenuWidth){
                    if(isOpen()){
                        closeMenu();
                    }
                }
                return true;
            default:
                break;

        }
        return super.onTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        /*boolean isIntercept = false;
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isIntercept = isOpen();
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;

        }
        return isIntercept;*/
        return false;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        if (mDrawerType) {
            float scale = l * 1.0f / mMenuWidth;  //1 ~ 0
            //调用属性动画,设TranslationX
            mMenu.setTranslationX(mMenuWidth * scale);
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    public void openMenu() {
        mIsOpen = true;
        this.smoothScrollTo(0, 0);
    }

    public void closeMenu() {
        mIsOpen = false;
        this.smoothScrollTo(mMenuWidth, 0);
    }

    public void toggleMenu() {
        if (mIsOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

}
