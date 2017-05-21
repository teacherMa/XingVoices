package com.example.xiaomage.xingvoices.feature.main.textComment;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;

public class TextComentPresenter extends BasePresenter<TextComentContract.View , TargetRepository> implements TextComentContract.Presenter {
    
    public TextComentPresenter(@NonNull TargetRepository repository, @NonNull TextComentContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        
    }
}
