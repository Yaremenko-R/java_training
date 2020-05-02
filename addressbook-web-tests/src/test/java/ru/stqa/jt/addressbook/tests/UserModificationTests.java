package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserData("Lee", "USA", "322233", "Bruce", "lee@mail.ru", "g1"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initGroupModification();
    app.getContactHelper().fillUserForm(new UserData("Duck", "Texas", "322233", "Donald", "doe@mail.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
