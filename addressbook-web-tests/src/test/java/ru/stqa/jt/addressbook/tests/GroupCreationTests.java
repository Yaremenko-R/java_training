package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.jt.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("g1", "g1", "g1"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.logout();
  }

}
