package com.example.xiaomage.xingvoices.feature.personal;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.framework.BasePresenterApi;
import com.example.xiaomage.xingvoices.model.personal.PersonalRepository;

public class PersonalPresenter extends BasePresenter<PersonalContract.View, PersonalRepository> implements PersonalContract.Presenter {

    public PersonalPresenter(@NonNull PersonalRepository repository, @NonNull PersonalContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }
}
