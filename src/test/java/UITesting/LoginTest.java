package UITesting;

import base.TestBase;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Login;

import static util.uiUtil.UIUtility.*;

public class LoginTest extends TestBase {

    Login login;

    @BeforeClass
    public void initLogin()
    {
        login = new Login(getDriver());
    }


    @Test
    public void txtUsernameVisibilityCheck()
    {
        boolean expected = true;
        boolean actual = checkAvailability(login.getTxtUsername());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void txtPasswordVisibilityCheck()
    {
        boolean expected = true;
        boolean actual = checkAvailability(login.getTxtPassword());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void btnLoginCheckEnability()
    {
        boolean expected = true;
        boolean actual = checkEnability(login.getBtnLogin());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void btnForgotPasswordCheckEnability()
    {
        boolean expected = true;
        boolean actual = checkEnability(login.getBtnForgotPassword());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void lblEmailSpellCheck()
    {
        String expected = "Email";
        String actual = checkSpelling(login.getLblEmail());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void lblPasswordSpellCheck()
    {
        String expected = "Password";
        String actual = checkSpelling(login.getLblPassword());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void lblPasswordPlaceholderCheck()
    {
        String expected = "Password";
    //    String actual = checkPlaceholder(login.getLblPassword());
        String actual = checkAttribute(login.getLblPassword(),"placeholder");
        Assert.assertEquals(actual,expected);
    }


    @Test
    public void lblPasswordFontSizeCheck()
    {
        String expected = "14px";
        String actual = checkCssValue(login.getLblPassword(),"font-size");
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void lblPasswordFontFamilyCheck()
    {
        String expected = "-apple-system, system-ui, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif";
        String actual = checkCssValue(login.getLblPassword(),"font-family");
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void btnLoginColorCheck()
    {
        String expected = "#2C8EDD";
        String elementColor = checkCssValue(login.getBtnLogin(),"background-color");
        String actual = Color.fromString(elementColor).asHex().toUpperCase();
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void btnLoginColorCheck2()
    {
        String expected = "#2C8EDD";
        String actual = checkCssValue(login.getBtnLogin(),"background-color");
         expected = Color.fromString(expected).asRgba();

        System.out.println("expected="+expected);
        System.out.println("actual="+actual);
        Assert.assertEquals(actual,expected);
    }
}
