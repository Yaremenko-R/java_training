package ru.stqa.jt.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("g1", "g1", "g1"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }

}
