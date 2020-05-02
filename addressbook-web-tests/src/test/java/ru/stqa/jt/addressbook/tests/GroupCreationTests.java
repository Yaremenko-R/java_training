package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.jt.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("g1", "g1", null));
    app.getNavigationHelper().returnToGroupPage();
  }
}
