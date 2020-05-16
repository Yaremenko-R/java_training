package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.UserData;
import java.util.List;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("g9", "g9", "g9"));
    }
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserData("Lee", "USA", "322233", "Bruce", "lee@mail.ru",null), true);
    }
  }

  @Test
  public void testUserDeletion() {
    app.goTo().homePage();
    List<UserData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
   }
}
