package com.example.sujin.rxtest.app;

import android.app.Application;
import com.example.sujin.rxtest.di.component.DaggerUserComponent;
import com.example.sujin.rxtest.di.component.UserComponent;
import com.example.sujin.rxtest.di.module.UserRepositoryModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sujin on 1/17/18.
 */

public class MyApplication extends Application {


  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    RealmConfiguration configuration = new RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded()
        .build();

    Realm.setDefaultConfiguration(configuration);

  }

}
