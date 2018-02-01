package com.example.sujin.rxtest.di.module;

import com.example.sujin.rxtest.repository.IUserRepository;
import com.example.sujin.rxtest.repository.impl.UserRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sujin on 1/18/18.
 */

@Module
public class UserRepositoryModule {

  @Provides
  public IUserRepository provideUserRepository() {
    return new UserRepository();
  }
}
