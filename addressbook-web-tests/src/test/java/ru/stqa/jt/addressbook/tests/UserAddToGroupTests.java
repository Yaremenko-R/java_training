package ru.stqa.jt.addressbook.tests;

import org.testng.Assert;
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
    GroupData targetGroup = groupsBefore.iterator().next();
    UserData contactToAdd = contactsBefore.iterator().next();
    Groups contactGroupsBeforeAdd = contactToAdd.getGroups();

    if (contactGroupsBeforeAdd.size() == 0) {
      app.goTo().homePage();
      app.contact().addToGroup(contactToAdd, targetGroup);
    } else if (contactGroupsBeforeAdd.contains(targetGroup)) {
      for (UserData contact : contactsBefore) {
        Groups contactGroups = contact.getGroups();
        if (!contactGroups.contains(targetGroup)) {
          contactToAdd = contact;
          break;
        }
      }
    } else {
      app.goTo().groupPage();
      targetGroup = new GroupData().withName("g9").withHeader("g9").withFooter("g9");
      app.group().create(targetGroup);
    }
    app.goTo().homePage();
    app.contact().addToGroup(contactToAdd, targetGroup);
    Groups contactGroupsAfterAdd = contactToAdd.getGroups();
    assertThat(contactGroupsAfterAdd, hasItem(targetGroup));
  }
}