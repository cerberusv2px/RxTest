package com.example.sujin.rxtest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import javax.inject.Inject;

/**
 * Created by sujin on 1/17/18.
 */

public class User extends RealmObject{

  @PrimaryKey
  private String id;
  @Required
  private String name;
  @Required
  private String address;

  public User(){

  }

  @Inject
  public User(String id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}
