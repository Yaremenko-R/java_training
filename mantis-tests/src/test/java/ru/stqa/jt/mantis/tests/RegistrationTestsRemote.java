package ru.stqa.jt.mantis.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jt.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTestsRemote extends TestBase{

  @DataProvider
  public Iterator<Object[]> validUsers() {
    List<Object[]> list = new ArrayList<>();
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "pass";
    String email = String.format("%s@localhost.localdomain", user);
    list.add(new Object[] {user, password, email});
    return list.iterator();
  }

  @Test(dataProvider = "validUsers")
  public void testRegistrationRemote(String user, String password, String email) throws IOException, MessagingException {
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user));
  }
}
