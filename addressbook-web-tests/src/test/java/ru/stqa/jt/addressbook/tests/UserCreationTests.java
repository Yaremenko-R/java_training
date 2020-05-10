package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.UserData;
import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    List<UserData> before = app.getContactHelper().getContactList();
    UserData contact = new UserData("Doe", "USA", "322233", "John", "doe@mail.ru", "g1");
    app.getContactHelper().createContact((contact), true);
    app.getNavigationHelper().returnToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super UserData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
