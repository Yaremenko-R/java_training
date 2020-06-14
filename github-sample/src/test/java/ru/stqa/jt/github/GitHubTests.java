package ru.stqa.jt.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("fa9d40f6d70fe77d016f844dfebd4e8d0ef0a88c ");
    RepoCommits commits = github.repos()
            .get(new Coordinates.Simple("Yaremenko-R", "java_training")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
