package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().returnToGroupPage();
    app.getSessionHelper().logout();
  }
}
