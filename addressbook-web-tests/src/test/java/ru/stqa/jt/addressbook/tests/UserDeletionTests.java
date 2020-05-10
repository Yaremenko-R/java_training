package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.UserData;
import java.util.List;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserData("Lee", "USA", "322233", "Bruce", "lee@mail.ru", "g1"), true);
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectUser(before.size() - 1);
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().deletionContactConfirmation();
    app.getContactHelper().msgContactDeletionWait();
    app.getNavigationHelper().gotoHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
   }
}
