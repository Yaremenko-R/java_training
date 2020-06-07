package ru.stqa.jt.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jt.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTestsLocal extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() {
    List<Object[]> list = new ArrayList<>();
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
    list.add(new Object[] {user, password, email});
    return list.iterator();
  }

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test(dataProvider = "validUsers")
  public void testRegistrationLocal(String user, String password, String email) throws IOException, MessagingException {
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
