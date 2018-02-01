package com.example.sujin.rxtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.sujin.rxtest.R;
import com.example.sujin.rxtest.activity.MainActivityContact.Presenter;
import com.example.sujin.rxtest.di.component.DaggerUserComponent;
import com.example.sujin.rxtest.di.component.UserComponent;
import com.example.sujin.rxtest.di.module.UserRepositoryModule;
import com.example.sujin.rxtest.model.User;
import com.example.sujin.rxtest.repository.impl.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContact.View {

  private static final String TAG = MainActivity.class.getSimpleName();

  UserRepository userRepository;

  MainActivityContact.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    presenter = new MainActivityPresenter(getApplicationContext(), this);

    UserComponent userComponent = DaggerUserComponent
        .builder()
        .userRepositoryModule(new UserRepositoryModule())
        .build();

    userRepository = userComponent.getUserRepository();
    onFetchData(userRepository);

  }

  private List<User> getUserData() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("1", "Ad", "A1"));
    userList.add(new User("2", "Bf", "A1"));
    userList.add(new User("3", "Cd", "C1"));
    return userList;
  }

  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void showProgress() {
    Log.d(TAG, "Show PROGRESS");
  }

  @Override
  public void hideProgress() {
    Log.d(TAG, "Hide Progress");
  }

  @Override
  public void onFetchData(UserRepository userRepository) {
    presenter.fetchData(userRepository);
  }

  @Override
  public void onInsertUser(UserRepository userRepository, List<User> userList) {
    presenter.insertUser(userRepository, userList);
  }

  @Override
  public void onUpdateUser(User user, UserRepository userRepository) {
  }

  @Override
  public void onFetchUser(String id, UserRepository userRepository) {
    presenter.fetchUser(id, userRepository);
  }
}
