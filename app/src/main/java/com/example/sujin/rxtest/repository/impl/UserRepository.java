package com.example.sujin.rxtest.repository.impl;

import com.example.sujin.rxtest.model.User;
import com.example.sujin.rxtest.repository.IUserRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by sujin on 1/17/18.
 */

public class UserRepository implements IUserRepository {

  @Inject
  public UserRepository() {
  }

  @Override
  public void addUser(List<User> user) {
    Realm realm = Realm.getDefaultInstance();
    try {
      realm.executeTransaction(new Transaction() {
        @Override
        public void execute(Realm realm) {
          realm.copyToRealmOrUpdate(user);
        }
      });
    } finally {
      if (realm != null) {
        realm.close();
      }
    }
  }

  @Override
  public Flowable<List<User>> getAllUser() {
    Realm realm = Realm.getDefaultInstance();
    List<User> userList = realm.copyFromRealm(realm.where(User.class).findAll());
    realm.close();
    return Flowable.just(userList);
    /*return realm.where(User.class)
        .findAllAsync()
        .asFlowable()
        .map(users -> {
          try {
            return realm.copyFromRealm(users);
          } finally {
            realm.close();
          }
        });*/
  }

  @Override
  public Flowable<User> getUserById(String id) {
    Realm realm = Realm.getDefaultInstance();
    User user = realm.copyFromRealm(realm.where(User.class).equalTo("id", id).findFirst());
    realm.close();
    return Flowable.just(user);
    /*return realm.where(User.class)
        .equalTo("id", id)
        .findFirstAsync()
        .asFlowable()
        .map(userObject -> {
          try {
            return realm.copyFromRealm(userObject);
          } finally {
            if (realm != null) {
              realm.close();public class UserComponent {

            }
          }
        });*/
  }

  @Override
  public Completable updateUser(User user) {
    Realm realm = Realm.getDefaultInstance();
    try {
      realm.executeTransaction(realm1 -> {
        realm1.copyToRealmOrUpdate(user);
      });
    } finally {
      if (realm != null) {
        realm.close();
      }
    }
    return Completable.complete();

  }


}
