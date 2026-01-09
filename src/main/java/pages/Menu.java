package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Menu {


    @FindBy (xpath = "//a[normalize-space()='Dashboard']")
    WebElement dashboard;

    @FindBy (xpath = "//span[normalize-space()='Clients']")
    WebElement clients;

    @FindBy (xpath = "//a[contains(text(),'Add Client')]")
    WebElement addClient;

    @FindBy (xpath = "//a[normalize-space()='View Clients']")
    WebElement viewClients;

    @FindBy (xpath = "//span[normalize-space()='Quotes']")
    WebElement quotes;

    @FindBy (xpath = "//a[@class='create-quote']")
    WebElement createQuote;

    @FindBy (xpath = "//a[normalize-space()='View Quotes']")
    WebElement viewQuotes;

    public Menu(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }


    public void clickOnDashboard() {
        dashboard.click();
    }

   /* public void clickOnClients() {
        clients.click();
    }*/

    public void clickOnAddClient() {
        clients.click();
        addClient.click();
    }

    public void clickOnViewClients() {
        clients.click();
        viewClients.click();
    }

  /*  public void clickOnQuotes() {
        quotes.click();
    }*/

    public void clickOnCreateQuote() {
        quotes.click();
        createQuote.click();
    }

    public void clickOnViewQuotes() {
        quotes.click();
        viewQuotes.click();
    }

}