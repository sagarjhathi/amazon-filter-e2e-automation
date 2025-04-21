package orangehr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import googleaggregatorpomupdated.DriverManager;

public class ContactSales {

	
	
	WebDriver driver;

    public ContactSales(WebDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='Form_getForm_FullName']")
    WebElement fullNameContactSales;

    @FindBy(xpath = "//input[@id='Form_getForm_Contact']")
    WebElement phoneNumberContactSales;

    @FindBy(xpath = "//input[@id='Form_getForm_Email']")
    WebElement businessEmailContactSales;

    @FindBy(xpath = "//input[@id='Form_getForm_CompanyName']")
    WebElement companyNameContactSales;

    @FindBy(xpath = "//input[@id='Form_getForm_Country']")
    WebElement countryDropDownContactSales;

    @FindBy(xpath = "//input[@id='Form_getForm_NoOfEmployees']")
    WebElement numberOfEmployeeContactSales;

    @FindBy(xpath = "//option[@value='Afghanistan']")
    WebElement countrySelectionFromDropDownContactSales;

    @FindBy(xpath = "//option[@value='< 10']")
    WebElement employeeSelectionFromDropDownContactSales;

    @FindBy(xpath = "//input[@id='Form_getForm_JobTitle']")
    WebElement jobTitleContactSales;

    @FindBy(xpath = "//textarea[@id='Form_getForm_Comment']")
    WebElement yourMessageContactSales;

    @FindBy(xpath = "//h4[@class='form-title']")
    WebElement errorTextAfterClickingGetFreeDemoContactSales;
    
    @FindBy(xpath = "//input[@id='Form_getForm_action_submitForm']")
    WebElement contactSalesSubmitButtonContactSales;
	
    
	
    
 // Fill text fields
    public void enterFullName(String fullName) {
        fullNameContactSales.clear();
        fullNameContactSales.sendKeys(fullName);
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberContactSales.clear();
        phoneNumberContactSales.sendKeys(phoneNumber);
    }

    public void enterBusinessEmail(String email) {
        businessEmailContactSales.clear();
        businessEmailContactSales.sendKeys(email);
    }

    public void enterCompanyName(String companyName) {
        companyNameContactSales.clear();
        companyNameContactSales.sendKeys(companyName);
    }

    public void enterJobTitle(String jobTitle) {
        jobTitleContactSales.clear();
        jobTitleContactSales.sendKeys(jobTitle);
    }

    public void enterYourMessage(String message) {
        yourMessageContactSales.clear();
        yourMessageContactSales.sendKeys(message);
    }

    // Dropdown interactions
    public void selectCountry() {
        countryDropDownContactSales.click();
        countrySelectionFromDropDownContactSales.click();
    }

    public void selectNumberOfEmployees() {
        numberOfEmployeeContactSales.click();
        employeeSelectionFromDropDownContactSales.click();
    }
    
    public void submitFormContactSales() {
    	contactSalesSubmitButtonContactSales.click();
    }

    // Get error message (optional validation)
    public String getErrorText() {
        return errorTextAfterClickingGetFreeDemoContactSales.getText();
    }

	
	
}
