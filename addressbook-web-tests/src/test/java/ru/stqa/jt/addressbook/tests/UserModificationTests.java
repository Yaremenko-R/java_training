package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.UserData;
import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

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
  public void testUserModification() {
    app.goTo().homePage();
    List<UserData> before = app.contact().list();
    int index = 0;
    UserData contact = new UserData(before.get(index).getId(),"Duck", "Texas", "322233", "Donald", "doe@mail.ru", null);
    app.contact().modify(index, contact);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super UserData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
