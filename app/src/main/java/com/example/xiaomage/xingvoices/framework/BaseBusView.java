package com.example.xiaomage.xingvoices.framework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xiaomage.xingvoices.event.EmptyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public abstract class BaseBusView<P extends BasePresenterApi> extends BaseView<P> {

    public BaseBusView(@NonNull Context context) {
        super(context);
    }

    public BaseBusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public BaseBusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public abstract void onEvent(EmptyEvent event);
}
