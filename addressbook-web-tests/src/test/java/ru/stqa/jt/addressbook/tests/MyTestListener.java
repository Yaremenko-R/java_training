package ru.stqa.jt.addressbook.tests;

import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.stqa.jt.addressbook.appmanager.ApplicationManager;
import ru.yandex.qatools.allure.annotations.Attachment;

public class MyTestListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
    saveScreenshot(app.takeScreenshot());
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] saveScreenshot(byte[] screenShot) {
    return screenShot;
  }
}

