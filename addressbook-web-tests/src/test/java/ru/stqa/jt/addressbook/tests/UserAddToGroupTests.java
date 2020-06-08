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
  public void testUserAddToGroup() {
    Users contactsBefore = app.db().users();
    Groups groupsBefore = app.db().groups();
    GroupData targetGroup = null;
    UserData contactToAdd = null;

    for (UserData contact : contactsBefore) {
      Groups contactGroups = contact.getGroups();

      for (GroupData group : groupsBefore) {
        if (!contactGroups.contains(group)) {
          targetGroup = group;
          contactToAdd = contact;
          break;
        } else {
          app.goTo().groupPage();
          targetGroup = new GroupData().withName("g9").withHeader("g9").withFooter("g9");
          app.group().create(targetGroup);
          contactToAdd = contactsBefore.iterator().next();
        }
      }
    }
    app.goTo().homePage();
    app.contact().addToGroup(contactToAdd, targetGroup);
    // Refresh data from DB before assert
    Users contactsAfter = app.db().users();
    Groups groupsAfter = app.db().groups();
    Groups contactGroupsAfterAdd = contactToAdd.getGroups();
    assertThat(contactGroupsAfterAdd, hasItem(targetGroup));
  }
}
