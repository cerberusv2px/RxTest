package com.example.sujin.rxtest.repository;

import com.example.sujin.rxtest.model.User;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.realm.RealmResults;
import java.util.List;

/**
 * Created by sujin on 1/17/18.
 */

public interface IUserRepository {

  void addUser(List<User> user);
  Flowable<List<User>> getAllUser();
  Flowable<User> getUserById(String id);
  Completable updateUser(User user);

}
