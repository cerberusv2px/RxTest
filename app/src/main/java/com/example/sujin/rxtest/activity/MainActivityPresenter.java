package com.example.sujin.rxtest.activity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.sujin.rxtest.activity.MainActivityContact.View;
import com.example.sujin.rxtest.model.User;
import com.example.sujin.rxtest.repository.impl.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by sujin on 1/18/18.
 */

public class MainActivityPresenter implements MainActivityContact.Presenter {

  private static final String TAG = MainActivityPresenter.class.getSimpleName();

  Context context;
  MainActivityContact.View view;

  public MainActivityPresenter(Context context,
      View view) {
    this.context = context;
    this.view = view;
    view.setPresenter(this);
  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

  @Override
  public void fetchData(UserRepository userRepository) {
    view.showProgress();
    Flowable<List<User>> userList = userRepository.getAllUser();
    userList
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .flatMapIterable(users -> users)
        .filter(users -> users.getAddress().equalsIgnoreCase("A1"))
        .subscribe(
            users -> Log.e(TAG, users.toString()),
            error -> view.hideProgress(),
            () -> view.hideProgress()
        );
  }

  @Override
  public void insertUser(UserRepository userRepository, List<User> userList) {
    view.showProgress();
    Observable<List<User>> userObservable = Observable.just(userList);
    userObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(users -> {
          // UserRepository userRepository = new UserRepository();
          userRepository.addUser(users);
          return Observable.just(users);
        })
        .subscribe(
            users -> Log.e(TAG, String.valueOf(users.size())),
            error -> {
              Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
              view.hideProgress();
            },
            () -> {
              Toast.makeText(context, "Completed", Toast.LENGTH_SHORT).show();
              view.hideProgress();
            });

  }

  @Override
  public void updateUser(User user, UserRepository userRepository) {
    Completable completable = userRepository.updateUser(user);
    completable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            () -> Log.d(TAG, "Completed"),
            error -> Log.e(TAG, "Error")
        );
  }

  @Override
  public void fetchUser(String id, UserRepository userRepository) {
    view.showProgress();
    Flowable<User> userObservable = userRepository.getUserById(id);
    userObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            user -> Log.e(TAG, user.toString()),
            error -> view.hideProgress(),
            () -> view.hideProgress()
        );
  }
}
