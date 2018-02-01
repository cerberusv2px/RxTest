package com.example.sujin.rxtest.activity;

import com.example.sujin.rxtest.BasePresenter;
import com.example.sujin.rxtest.BaseView;
import com.example.sujin.rxtest.model.User;
import com.example.sujin.rxtest.repository.impl.UserRepository;
import java.util.List;

/**
 * Created by sujin on 1/18/18.
 */

public interface MainActivityContact {

  interface View extends BaseView<Presenter>{
    void showProgress();
    void hideProgress();
    void onFetchData(UserRepository userRepository);
    void onInsertUser(UserRepository userRepository, List<User> userList);
    void onUpdateUser(User user, UserRepository userRepository);
    void onFetchUser(String id, UserRepository userRepository);
  }

  interface Presenter extends BasePresenter{
    void fetchData(UserRepository userRepository);
    void insertUser(UserRepository userRepository, List<User> userList);
    void updateUser(User user, UserRepository userRepository);
    void fetchUser(String id, UserRepository userRepository);
  }
}
