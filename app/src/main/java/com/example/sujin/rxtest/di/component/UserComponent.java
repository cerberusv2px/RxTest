package com.example.sujin.rxtest.di.component;

import com.example.sujin.rxtest.di.module.UserRepositoryModule;
import com.example.sujin.rxtest.repository.impl.UserRepository;
import dagger.Component;

/**
 * Created by sujin on 1/18/18.
 */
@Component(modules = {UserRepositoryModule.class})
public interface UserComponent {

  UserRepository getUserRepository();
}
