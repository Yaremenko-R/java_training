package ru.stqa.jt.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class UserData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Transient
  private File photo;

  public Groups getGroups() {
    return new Groups(groups);
  }

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
            ", address='" + address + '\'' +
            ", home='" + home + '\'' +
            ", firstname='" + firstname + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(lastname, userData.lastname) &&
            Objects.equals(address, userData.address) &&
            Objects.equals(home, userData.home) &&
            Objects.equals(firstname, userData.firstname) &&
            Objects.equals(email, userData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, address, home, firstname, email);
  }

  public UserData inGroup(GroupData group){
    groups.add(group);
    return this;
  }
}
