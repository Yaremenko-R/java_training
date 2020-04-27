package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().deletionContactConfirmation();
    app.getContactHelper().msgContactDeletionWait();
    app.getNavigationHelper().gotoHomePage();
   }
}
