package ru.stqa.jt.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jt.mantis.model.Issue;
import ru.stqa.jt.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws IOException, ServiceException {
    //skipIfNotFixedInMantis(2);
    skipIfNotFixedInBugify(90);
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws IOException, ServiceException {
    //skipIfNotFixedInMantis(1);
    skipIfNotFixedInBugify(93);
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
  }
}
