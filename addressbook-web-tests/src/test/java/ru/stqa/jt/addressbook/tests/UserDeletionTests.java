package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserData("Lee", "USA", "322233", "Bruce", "lee@mail.ru", "g1"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().deletionContactConfirmation();
    app.getContactHelper().msgContactDeletionWait();
    app.getNavigationHelper().gotoHomePage();
   }
}
