package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.jt.addressbook.model.GroupData;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("g3", "g3", "g3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().select(index);
    app.group().delete();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
