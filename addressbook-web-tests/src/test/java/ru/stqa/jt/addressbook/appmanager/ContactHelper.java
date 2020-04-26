package ru.stqa.jt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.jt.addressbook.model.UserData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillUserForm(UserData userData) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHome());
    type(By.name("email"), userData.getEmail());
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initGroupModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }
}
