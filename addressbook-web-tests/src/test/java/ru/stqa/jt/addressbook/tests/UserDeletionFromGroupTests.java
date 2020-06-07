package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.Groups;
import ru.stqa.jt.addressbook.model.UserData;
import ru.stqa.jt.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionFromGroupTests extends TestBase {

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
  public void testUserDeletionFromGroup() {
    Users contactsBefore = app.db().users();
    Groups groupsBefore = app.db().groups();
    GroupData targetGroupRand = groupsBefore.iterator().next();
    UserData contactToDelRand = contactsBefore.iterator().next();
    UserData contactToDel = null;
    GroupData targetGroup = null;

    for (UserData contact : contactsBefore) {
      Groups contactGroups = contact.getGroups();
      if (contactGroups.size() > 0) {
        contactToDel = contact;
        targetGroup = contactToDel.getGroups().iterator().next();
        break;
      } else {
        app.goTo().homePage();
        app.contact().addToGroup(contactToDelRand, targetGroupRand);
      }
    }

    for (UserData contact : contactsBefore) {
      Groups contactGroups = contact.getGroups();
      for (GroupData group : groupsBefore) {
        if (contactGroups.contains(group)) {
          targetGroup = group;
          contactToDel = contact;
          break;
        }
      }
    }

    app.goTo().homePage();
    app.contact().deleteFromGroup(contactToDel, targetGroup);
    Groups contactGroupsAfterDel = contactToDel.getGroups();
    assertThat(contactGroupsAfterDel, not(hasItem(targetGroup)));
  }
}