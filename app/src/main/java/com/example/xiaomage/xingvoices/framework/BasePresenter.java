package com.example.xiaomage.xingvoices.framework;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.model.BaseResponse;

/**
 * Init view and repository and bind view and presenter .
 * <p>
 */
public abstract class BasePresenter<V extends BaseViewApi, M extends BaseRepository> implements BasePresenterApi {

    private final V mView;
    private final M mRepository;

    public BasePresenter(@NonNull M repository, @NonNull V view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    protected M getRepository() {
        return mRepository;
    }

    protected V getView() {
        return mView;
    }

    /**
     * To check if the response from model is valid.
     * True means valid.
     *
     * @param response target response
     * @return result
     */
    public boolean checkResponseValid(BaseResponse response) {
        return null != response && null != response.getBody();
    }
}
