package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.framework.BaseView;

public class TextComentView extends BaseView<TextComentContract.Presenter> implements TextComentContract.View {


    public TextComentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }
}
