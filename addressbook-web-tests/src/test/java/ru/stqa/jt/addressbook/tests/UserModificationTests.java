package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.UserData;
import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("g9", "g9", "g9"));
    }
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserData("Lee", "USA", "322233", "Bruce", "lee@mail.ru",null), true);
    }
    app.getNavigationHelper().gotoHomePage();
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(0);
    UserData contact = new UserData(before.get(0).getId(),"Duck", "Texas", "322233", "Donald", "doe@mail.ru", null);
    app.getContactHelper().fillUserForm((contact), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(0);
    before.add(contact);
    Comparator<? super UserData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
