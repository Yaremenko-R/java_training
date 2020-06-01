package ru.stqa.jt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.jt.addressbook.model.GroupData;
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
    attach(By.name("photo"), userData.getPhoto());

    if (creation) {
      if (new UserData().getGroups().size() > 0) {
        Assert.assertTrue(userData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(userData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addToGroup(UserData contact, GroupData group) {
    selectById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.name("add"));
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
    //wd.findElement(By.cssSelector("a[href^=\"edit.php?id=" + id + "")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
    for (WebElement element : elements) {
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
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      UserData contact = new UserData().withId(id).withLastname(lastname).withFirstname(firstname)
              .withAllEmails(allEmails).withAddress(address).withAllPhones(allPhones);
      userCache.add(contact);
    }
    return userCache;
  }

  public UserData infoFromEditForm(UserData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new UserData()
            .withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobile(mobile).withWork(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
