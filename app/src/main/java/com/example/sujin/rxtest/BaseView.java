package com.example.sujin.rxtest;

/**
 * Created by sujin on 1/18/18.
 */

public interface BaseView<T extends BasePresenter> {

  void setPresenter(T presenter);
}
