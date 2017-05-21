package com.example.xiaomage.xingvoices.feature.personal;

import android.support.annotation.NonNull;

import com.example.xiaomage.xingvoices.framework.BasePresenter;
import com.example.xiaomage.xingvoices.model.bean.RemoteVoice.RemoteVoice;
import com.example.xiaomage.xingvoices.model.bean.User.XingVoiceUser;
import com.example.xiaomage.xingvoices.model.personal.PersonalRepository;

import java.util.ArrayList;
import java.util.List;

public class PersonalPresenter extends BasePresenter<PersonalContract.View, PersonalRepository> implements PersonalContract.Presenter {

    public PersonalPresenter(@NonNull PersonalRepository repository, @NonNull PersonalContract.View view) {
        super(repository, view);
    }

    @Override
    public void start() {
        XingVoiceUser xingVoiceUser = getView().getXingVoiceUser();
        if(null == xingVoiceUser){
            return;
        }
        requestUserVoices(xingVoiceUser);
    }

    @Override
    public void requestUserVoices(XingVoiceUser xingVoiceUser) {
        List<RemoteVoice> voices = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            voices.add(new RemoteVoice());
        }
        getView().loadData(voices);
    }
}
