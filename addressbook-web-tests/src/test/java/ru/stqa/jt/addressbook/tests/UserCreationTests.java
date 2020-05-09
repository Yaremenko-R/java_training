package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new UserData("Doe", "USA", "322233", "John", "doe@mail.ru", "g1"), true);
    app.getNavigationHelper().returnToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
