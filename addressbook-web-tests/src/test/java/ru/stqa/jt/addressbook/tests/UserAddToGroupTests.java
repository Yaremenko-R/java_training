package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.Groups;
import ru.stqa.jt.addressbook.model.UserData;
import ru.stqa.jt.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
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
    UserData contact = new UserData().inGroup(gBefore.iterator().next());
    assertThat(app.contact().count(), equalTo(uBefore.size() + 1));
    Users uAfter = app.db().users();
    assertThat(uAfter, equalTo(
            uBefore.withAdded(contact.withId(uAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }
}
