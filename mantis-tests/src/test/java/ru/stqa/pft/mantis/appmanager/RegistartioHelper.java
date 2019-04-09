package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistartioHelper {

  private final ApplicationManager app;
  private WebDriver wd;

  public RegistartioHelper(ApplicationManager app) {
    this.app=app;
    wd=app.getDriver();
  }

  public void start(String username, String email){
    wd.get(app.getProperty("web.baseUrl")+"/login.php"+"/signup_page.php");

  }

}
