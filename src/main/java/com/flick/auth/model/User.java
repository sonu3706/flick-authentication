package com.flick.auth.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.vertx.core.json.JsonObject;


public class User {

  public static final String DB_TABLE = "user";

  private String userId;
  private String userEmail;
  private String firstName;
  private String lastName;
  private String password;

  public User() {
  }

  public User(String userId, String userEmail, String firstName, String lastName, String password) {
    this.userId = userId;
    this.userEmail = userEmail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  public User(JsonObject obj) {
    userId = obj.getString("userId");
    userEmail = obj.getString("userEmail");
    firstName = obj.getString("firstName");
    lastName = obj.getString("lastName");
    password = obj.getString("password");

  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
      "userId='" + userId + '\'' +
      ", userEmail='" + userEmail + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
