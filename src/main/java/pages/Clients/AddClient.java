package pages.Clients;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AddClient {
    // Preferred id locators where possible — faster and more robust than XPath for simple id lookups
    @FindBy(id = "client_name")
    WebElement clientName;

    @FindBy(id = "client_surname")
    WebElement clientSurname;

    @FindBy(id = "client_address_1")
    WebElement streetAddress1;

    @FindBy(id = "client_address_2")
    WebElement streetAddress2;

    @FindBy(id = "client_city")
    WebElement city;

    @FindBy(id = "client_state")
    WebElement state;

    @FindBy(id = "client_zip")
    WebElement zipCode;

    @FindBy(id = "client_phone")
    WebElement phoneNumber;

    @FindBy(id = "client_fax")
    WebElement faxNumber;

    @FindBy(id = "client_mobile")
    WebElement mobileNumber;

    @FindBy(id = "client_email")
    WebElement emailAddress;

    @FindBy(id = "client_web")
    WebElement webAddress;

    @FindBy(id = "client_vat_id")
    WebElement vATID;

    @FindBy(id = "client_tax_code")
    WebElement taxesCode;

    @FindBy(id = "btn-submit")
    WebElement save;

    // select2 uses an inline container; selecting by id is okay here
    @FindBy(id = "select2-client_language-container")
    WebElement languageContainer;

    // generic searchbox used by select2 dropdowns — we will wait for visibility before using it
    @FindBy(css = "input[role='searchbox']")
    WebElement searchBox;


    WebDriver driver;

    // Helper wait instance (short-lived per method to avoid global state)
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Select language from select2 control. This method will:
     * - click the container to open the dropdown
     * - wait for the search box to be visible
     * - type the language and click the matching result
     * Using explicit waits makes interaction more reliable on slower CI agents.
     */
    public void setLanguage(String language)
    {
        languageContainer.click();
        // wait until search box appears and is usable
        getWait().until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(language);
        // wait for the specific option to appear and click it
        By option = By.xpath("//li[normalize-space()='"+language+"']");
        getWait().until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }

    @FindBy(id = "select2-client_country-container")
    WebElement countryContainer;

    /**
     * Select country from select2 control.
     */
    public void setCountry(String country)
    {
        countryContainer.click();
        getWait().until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(country);
        By option = By.xpath("//li[normalize-space()='"+country+"']");
        getWait().until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }

    @FindBy(id = "select2-client_gender-container")
    WebElement genderContainer;

    /**
     * Select gender from select2 control.
     */
    public void setGender(String gender)
    {
        genderContainer.click();
        By option = By.xpath("//li[normalize-space()='"+gender+"']");
        getWait().until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
    }

    @FindBy(id = "client_birthdate")
    WebElement birthdate;

    /**
     * Set birth date. Some datepicker widgets don't accept sendKeys or require events to be fired
     * so we set the value via JS and dispatch a change event to ensure the application picks it up.
     */
    public void setBirthDate(String bDate)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Set the value and dispatch a native 'change' event so listeners notice the update
        js.executeScript("arguments[0].value = '" + bDate + "'; arguments[0].dispatchEvent(new Event('change'));", birthdate);
    }


    // Constructor to initialize the WebElements
    public AddClient(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with the text boxes
    public void setClientName(String name) {
        clientName.sendKeys(name);
    }

    public void setClientSurname(String surname) {
        clientSurname.sendKeys(surname);
    }

    public void setStreetAddress1(String address1) {
        streetAddress1.sendKeys(address1);
    }

    public void setStreetAddress2(String address2) {
        streetAddress2.sendKeys(address2);
    }

    public void setCity(String cityName) {
        city.sendKeys(cityName);
    }

    public void setState(String stateName) {
        state.sendKeys(stateName);
    }

    public void setZipCode(String zip) {
        zipCode.sendKeys(zip);
    }

    public void setPhoneNumber(String phone) {
        phoneNumber.sendKeys(phone);
    }

    public void setFaxNumber(String fax) {
        faxNumber.sendKeys(fax);
    }

    public void setMobileNumber(String mobile) {
        mobileNumber.sendKeys(mobile);
    }

    public void setEmailAddress(String email) {
        emailAddress.sendKeys(email);
    }

    public void setWebAddress(String web) {
        webAddress.sendKeys(web);
    }

    public void setVATID(String vatId) {
        vATID.sendKeys(vatId);
    }

    public void setTaxesCode(String taxCode) {
        taxesCode.sendKeys(taxCode);
    }

    public void clickSave()
    {
        save.click();
    }
}
