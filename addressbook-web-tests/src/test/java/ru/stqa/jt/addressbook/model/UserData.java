package ru.stqa.jt.addressbook.model;

import java.io.File;
import java.util.Objects;

public class UserData {
  private int id = Integer.MAX_VALUE;
  private String lastname;
  private String address;
  private String home;
  private String mobile;
  private String work;
  private String firstname;
  private String email;
  private String email2;
  private String email3;
  private String group;
  private String allPhones;
  private String allEmails;
  private File photo;

  public UserData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withHome(String home) {
    this.home = home;
    return this;
  }

  public UserData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public UserData withWork(String work) {
    this.work = work;
    return this;
  }

  public UserData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
  }

  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public int getId() {
    return id;
  }

  public File getPhoto() {
    return photo;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
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

  public String getGroup() {
    return group;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(lastname, userData.lastname) &&
            Objects.equals(firstname, userData.firstname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, firstname);
  }
}
