package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("g3", "g3", "g3"));
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("g01", "g02", "g03"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().returnToGroupPage();
  }
}
