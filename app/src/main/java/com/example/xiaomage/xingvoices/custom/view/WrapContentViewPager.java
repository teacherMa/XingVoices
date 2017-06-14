package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xiaomage.xingvoices.api.OnVpScrollListener;

/**
 * Created by xiaomage on 2017/5/21.
 */

public class WrapContentViewPager extends ViewPager {

    private OnVpScrollListener mOnVpScrollListener;

    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for(int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                if(ev.getX()>ev.getY()){
                    mOnVpScrollListener.onVpScroll(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                mOnVpScrollListener.onVpScroll(false);
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnVpScrollListener(OnVpScrollListener onVpScrollListener) {
        mOnVpScrollListener = onVpScrollListener;
    }
}
