package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    /*WebDriver driver;
    WebElement element = driver.findElement(By.id("email"));*/

    @FindBy (id = "login-username" )
    WebElement txtUsername;

    @FindBy (id ="login-password")
    WebElement txtPassword;

    @FindBy (xpath = "//input[@name='submit']")
    WebElement btnLogin;

    @FindBy (linkText = "Forgot your password?")
    WebElement btnForgotPassword;

    @FindBy (xpath = "//label[@for='email']")
    WebElement lblEmail;

    public WebElement getTxtUsername() {
        return txtUsername;
    }

    public WebElement getTxtPassword() {
        return txtPassword;
    }

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    public WebElement getBtnForgotPassword() {
        return btnForgotPassword;
    }

    public WebElement getLblEmail() {
        return lblEmail;
    }

    public WebElement getLblPassword() {
        return lblPassword;
    }

    public WebElement getLogoIcon() {
        return logoIcon;
    }

    @FindBy (xpath = "//label[@for='password']")
    WebElement lblPassword;

    @FindBy (xpath = "//img[@class='login-logo img-responsive']")
    WebElement logoIcon;

    public Login(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    public void setTxtUsername(String username)
    {
        txtUsername.sendKeys(username);
    }

    public void setTxtPassword(String password)
    {
        txtPassword.sendKeys(password);
    }

    public void clickLogin()
    {
        btnLogin.click();
    }

    public void clickForgotPassword()
    {
        btnForgotPassword.click();
    }
}

