package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.UserData;

import java.util.Set;

public class UserCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("g9").withHeader("g9").withFooter("g9"));
    }
  }

  @Test
  public void testUserCreation() {
    app.goTo().homePage();
    Set<UserData> before = app.contact().all();
    UserData contact = new UserData()
            .withLastname("Doe").withAddress("USA")
            .withHome("322233").withFirstname("John").withEmail("doe@mail.ru");
    app.contact().create((contact), true);
    Set<UserData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
