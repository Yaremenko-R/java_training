package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.Groups;
import ru.stqa.jt.addressbook.model.UserData;
import ru.stqa.jt.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("g9").withHeader("g9").withFooter("g9"));
    }
    if (app.db().users().size() == 0) {
      app.contact().create(new UserData()
              .withLastname("Lee").withAddress("USA").withHome("322233")
              .withFirstname("Bruce").withEmail("lee@mail.ru"), true);
    }
  }

  @Test
  public void testSomeUserAddToSomeGroup() {
    app.goTo().homePage();
    Users uBefore = app.db().users();
    Groups gBefore = app.db().groups();
    GroupData group = gBefore.iterator().next();
    UserData contactToAdd = uBefore.iterator().next();
    if (contactToAdd.getGroups().contains(group)) {
      contactToAdd = uBefore.iterator().next();
    }
    app.contact().addToGroup(contactToAdd, group);
    assertThat(contactToAdd.getGroups(), hasItem(group));
  }
}
