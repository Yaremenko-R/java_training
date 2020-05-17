package ru.stqa.jt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jt.addressbook.model.UserData;
import ru.stqa.jt.addressbook.model.Users;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHome());
    type(By.name("email"), userData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void selectByIndex(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initContactModificationByIndex(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href^=\"edit.php?id=" + id + "")).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void msgContactDeletionWait() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void deletionContactConfirmation() {
    wd.switchTo().alert().accept();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void create(UserData userData, boolean creation) {
    click(By.linkText("add new"));
    fillUserForm(userData, creation);
    submitContactCreation();
    userCache = null;
    returnToHomePage();
  }

  public void modifyByIndex(int index, UserData contact) {
    initContactModificationByIndex(index);
    fillUserForm((contact), false);
    submitContactModification();
    userCache = null;
    returnToHomePage();
  }

  public void modifyById(UserData contact) {
    initContactModificationById(contact.getId());
    fillUserForm((contact), false);
    submitContactModification();
    userCache = null;
    returnToHomePage();
  }

  public void deleteByIndex(int index) {
    selectByIndex(index);
    deleteSelectedUsers();
    deletionContactConfirmation();
    msgContactDeletionWait();
    userCache = null;
  }

  public void deleteById(UserData contact) {
    selectById(contact.getId());
    deleteSelectedUsers();
    deletionContactConfirmation();
    msgContactDeletionWait();
    userCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<UserData> list() {
    List<UserData> contacts = new ArrayList<UserData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      UserData contact = new UserData().withId(id).withLastname(lastname).withFirstname(firstname);
      contacts.add(contact);
    }
    return contacts;
  }

  private Users userCache = null;

  public Users all() {
    if (userCache != null) {
      return new Users(userCache);
    }
    userCache = new Users();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements ) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      UserData contact = new UserData().withId(id).withLastname(lastname).withFirstname(firstname);
      userCache.add(contact);
    }
    return userCache;
  }
}
