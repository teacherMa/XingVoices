package com.example.xiaomage.xingvoices.utils;

import com.example.xiaomage.xingvoices.model.main.MainRepository;
import com.example.xiaomage.xingvoices.model.main.local.MainLocalDS;
import com.example.xiaomage.xingvoices.model.main.remote.MainRemoteDS;
import com.example.xiaomage.xingvoices.model.personal.PersonalRepository;
import com.example.xiaomage.xingvoices.model.personal.local.PersonalLocalDS;
import com.example.xiaomage.xingvoices.model.personal.remote.PersonalRemoteDS;
import com.example.xiaomage.xingvoices.model.record.RecordRepository;
import com.example.xiaomage.xingvoices.model.record.local.RecordLocalDS;
import com.example.xiaomage.xingvoices.model.record.remote.RecordRemoteDS;

/**
 * Inject repository .
 * <p>
 */

public class Injection {

    public static MainRepository provideMainRepository() {
        return MainRepository.getInstance(
                MainLocalDS.getInstance(),
                MainRemoteDS.getInstance()
        );
    }

    public static RecordRepository provideRecordRepository(){
        return RecordRepository.getInstance(
                RecordLocalDS.getInstance(),
                RecordRemoteDS.getInstance()
        );
    }

    public static PersonalRepository providePersonalRepository(){
        return PersonalRepository.getInstance(
                PersonalLocalDS.getInstance(),
                PersonalRemoteDS.getInstance()
        );
    }
}
