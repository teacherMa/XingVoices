package com.example.xiaomage.xingvoices.framework;

public interface BaseViewApi<T extends BasePresenterApi> {

    void setPresenter(T presenter);

    boolean isActive();
}
