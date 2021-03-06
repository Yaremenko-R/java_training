package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.UserData;
import ru.stqa.jt.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

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
  public void testUserModification() {
    app.goTo().homePage();
    Users before = app.db().users();
    UserData modifiedContact = before.iterator().next();
    UserData contact = new UserData()
            .withId((modifiedContact.getId())).withLastname("Duck").withAddress("Texas")
            .withHome("322233").withFirstname("Donald").withEmail("doe@mail.ru");
    app.contact().modifyById(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
