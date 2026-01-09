package util;

import base.TestBase;
import org.testng.annotations.BeforeClass;
import pages.Login;

import java.io.IOException;

import static utility.ConfigReader.getPassword;
import static utility.ConfigReader.getUsername;

public class DoLogin extends TestBase {

    @BeforeClass
    public void doLogin() throws IOException {
        Login login = new Login(getDriver());

        login.setTxtUsername(getUsername());
        login.setTxtPassword(getPassword());
        login.clickLogin();
    }
}