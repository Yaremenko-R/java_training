package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.GroupData;
import ru.stqa.jt.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("g9").withHeader("g9").withFooter("g9"));
    }
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserData()
              .withLastname("Lee").withAddress("USA").withHome("322233")
              .withFirstname("Bruce").withEmail("lee@mail.ru"), true);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    UserData contact = app.contact().all().iterator().next();
    UserData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
    assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
