package ru.stqa.jt.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jt.mantis.model.MailMessage;
import ru.stqa.jt.mantis.model.UserData;
import ru.stqa.jt.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    Users mantisUsers = app.db().users();
    UserData user = mantisUsers.iterator().next();
    app.registration().adminLogin();
    app.registration().resetUserPassword(user.getId());
    List<MailMessage> mailMessages = app.mail().waitForMail(1,50000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, "password");
    assertTrue(app.newSession().login(user.getUsername()));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
