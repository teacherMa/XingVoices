package com.example.xiaomage.xingvoices.model.personal.local;

import com.example.xiaomage.xingvoices.model.personal.PersonalDataSource;

public class PersonalLocalDS implements PersonalDataSource {
    private PersonalLocalDS() {
    }

    public static PersonalLocalDS getInstance() {
        return PersonalLocalDS.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PersonalLocalDS INSTANCE = new PersonalLocalDS();
    }
}
