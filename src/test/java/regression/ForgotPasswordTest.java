package regression;

import org.testng.annotations.Test;
import pages.ForgotPassword;
import pages.Login;

public class ForgotPasswordTest extends base.TestBase {

    @Test
    public void forgotPasswordTest()
    {

        Login login = new Login(getDriver());
        login.clickForgotPassword();

        ForgotPassword forgotPassword = new ForgotPassword(getDriver());
        forgotPassword.setTxtEmail("shivshankarpatil1996@gmail.com");
        forgotPassword.clickReset();
    }
}
