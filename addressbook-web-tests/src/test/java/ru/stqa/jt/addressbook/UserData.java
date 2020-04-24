package ru.stqa.jt.addressbook;

public class UserData {
  private final String lastname;
  private final String address;
  private final String home;
  private final String firstname;
  private final String email;

  public UserData(String lastname, String address, String home, String firstname, String email) {
    this.lastname = lastname;
    this.address = address;
    this.home = home;
    this.firstname = firstname;
    this.email = email;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getEmail() {
    return email;
  }
}
