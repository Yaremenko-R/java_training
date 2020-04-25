package ru.stqa.jt.addressbook;

import org.testng.annotations.Test;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    addNewUserPage();
    fillUserForm(new UserData("Doe", "USA", "322233", "John", "doe@mail.ru"));
    gotoHomePage();
  }
}
