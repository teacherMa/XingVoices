package com.example.xiaomage.xingvoices.model;

import com.example.xiaomage.xingvoices.model.bean.User.User;

import java.util.List;

import io.realm.Realm;

/**
 * Created by peng on 2017/4/24.
 */

public class UserManager {
    private User mCurrentUser;

    private static final UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mCurrentUser = realm.where(User.class).findFirst();
            }
        });
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(final User user) {
        mCurrentUser = user;
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public void logOut() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                List<User> users = realm.where(User.class).findAll();
                for (User user : users) {
                    user.deleteFromRealm();
                }
                mCurrentUser = null;
            }
        });
    }
}
