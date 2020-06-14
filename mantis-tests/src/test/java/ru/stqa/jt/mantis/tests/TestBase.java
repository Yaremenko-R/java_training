package ru.stqa.jt.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jt.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

  public static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.stop();
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
  }

  public boolean isMantisIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = app.soap().getMantisConnect();
    IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
    String issueStatus = issue.getStatus().getName();
    return !(issueStatus.equals("resolved") | issueStatus.equals("closed"));
  }

  public void skipIfNotFixedInMantis(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isMantisIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isBugifyIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsedIssue = new JsonParser().parse(json);
    String issueStatus  = parsedIssue.getAsJsonObject().get("issues")
            .getAsJsonArray().get(0).getAsJsonObject().get("state_name").toString().replace("\"", "");
    return !(issueStatus.equals("Resolved") | issueStatus.equals("Closed"));
  }

  public void skipIfNotFixedInBugify(int issueId) throws IOException {
    if (isBugifyIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }

}
