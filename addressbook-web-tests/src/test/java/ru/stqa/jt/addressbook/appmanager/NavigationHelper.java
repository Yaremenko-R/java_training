package ru.stqa.jt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void gotoAddNewUserPage() {
    click(By.linkText("add new"));
  }
}
