package com.example.xiaomage.xingvoices.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xiaomage.xingvoices.R;

/**
 * Created by xiaomage on 2017/5/3.
 */

public class RatioLayout extends LinearLayout {
    private Context context;
    private AttributeSet attrs;
    private int defStyleAttr;
    DisplayMetrics dm;

    /**
     * ratio = LayoutHeightSize / LayoutWidthSize
     */
    private float ratio;

    public RatioLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        this.context = context;
        this.attrs = attrs;
        this.defStyleAttr = defStyleAttr;
        if (attrs != null) {
            ratio = 0.0f;
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout,
                    defStyleAttr, 0);
            try {
                ratio = a.getFloat(R.styleable.RatioLayout_ratio, 0.0f);
            } finally {
                a.recycle();
            }
        }
    }

    /**
     * ratio==100.0f，width=parent.getWidth/2;height=window.widthPixels/3
     * ratio == -1.0f，使长宽相等且取小值。
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                getDefaultSize(0, heightMeasureSpec));
        if (ratio >= 0.0f) {
            int childWidthSize = getMeasuredWidth();
            int childHeightSize = (int) (childWidthSize * ratio);
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidthSize,
                    View.MeasureSpec.EXACTLY);
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeightSize,
                    View.MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
