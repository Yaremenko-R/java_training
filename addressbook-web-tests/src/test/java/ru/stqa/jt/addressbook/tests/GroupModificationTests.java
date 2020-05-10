package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("g3", "g3", "g3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup(1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(1).getId(),"g01", "g02", "g03");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
