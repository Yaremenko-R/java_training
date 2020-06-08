package ru.stqa.jt.mantis.model;

public class UserData {

  public int id;
  public String username;
  public String email;

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }
}