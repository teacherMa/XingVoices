package com.example.xiaomage.xingvoices.model.personal.remote;

import com.example.xiaomage.xingvoices.model.personal.PersonalDataSource;

public class PersonalRemoteDS implements PersonalDataSource {
    private PersonalRemoteDS() {
    }

    public static PersonalRemoteDS getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PersonalRemoteDS INSTANCE = new PersonalRemoteDS();
    }
}
