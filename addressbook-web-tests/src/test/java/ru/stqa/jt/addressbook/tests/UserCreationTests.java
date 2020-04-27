package ru.stqa.jt.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jt.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    app.getNavigationHelper().gotoAddNewUserPage();
    app.getContactHelper().fillUserForm(new UserData("Doe", "USA", "322233", "John", "doe@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }
}
