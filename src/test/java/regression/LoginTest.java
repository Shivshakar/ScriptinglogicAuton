package regression;

import static utility.ConfigReader.getPassword;
import static utility.ConfigReader.getUsername;

import base.TestBase;
import java.io.IOException;
import org.testng.annotations.Test;
import pages.Login;

public class LoginTest extends TestBase {

  @Test
  public void loginTest() throws IOException {
    Login login = new Login(getDriver());

    login.setTxtUsername(getUsername());
    login.setTxtPassword(getPassword());
    login.clickLogin();
  }
}
