package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("g3").withHeader("g3").withFooter("g3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().deleteById(deletedGroup);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}
