package orangehr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import googleaggregatorpomupdated.DriverManager;

public class DemoPage {

	
	WebDriver driver;
	public DemoPage(WebDriver driver) {
        this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath="//input[@id='Form_getForm_FullName']")
	WebElement fullNameDemoPage;
	
	@FindBy(xpath="//input[@id='Form_getForm_Contact']")
	WebElement phoneNumberDemoPage;
	
	@FindBy(xpath="//input[@id='Form_getForm_Email']")
	WebElement businessEmailDemoPage;
	
	@FindBy(xpath="//input[@id='Form_getForm_CompanyName']")
	WebElement companyNameDemoPage;
	
	@FindBy(xpath="//input[@id='Form_getForm_Country']")
	WebElement countryDropDownDemoPage;
	
	@FindBy(xpath="//input[@id='Form_getForm_NoOfEmployees']")
	WebElement numberOfEmployeeDemoPage;
	
	@FindBy(xpath="//option[@value='Afghanistan']")
	WebElement countryselectionFromDropDownDemoPage;
	
	@FindBy(xpath="//option[@value='< 10']")
	WebElement employeeSelectionFromDropDownDemoPage;
	
	
	@FindBy(xpath="//input[@id='Form_getForm_action_submitForm']")
	WebElement getFreeDemoSubmitButtonDemoPage;
	
	
	@FindBy(xpath="//h4[@class='form-title']")
	WebElement errorTextAfterClickingGetFreeDemo;
	
	
	
	// Fill input fields
	public void enterFullName(String fullName) {
	    fullNameDemoPage.clear();
	    fullNameDemoPage.sendKeys(fullName);
	}

	public void enterPhoneNumber(String phoneNumber) {
	    phoneNumberDemoPage.clear();
	    phoneNumberDemoPage.sendKeys(phoneNumber);
	}

	public void enterBusinessEmail(String email) {
	    businessEmailDemoPage.clear();
	    businessEmailDemoPage.sendKeys(email);
	}

	public void enterCompanyName(String companyName) {
	    companyNameDemoPage.clear();
	    companyNameDemoPage.sendKeys(companyName);
	}

	// Select dropdown options
	public void selectCountry() {
	    countryDropDownDemoPage.click();
	    countryselectionFromDropDownDemoPage.click();
	}

	public void selectNumberOfEmployees() {
	    numberOfEmployeeDemoPage.click();
	    employeeSelectionFromDropDownDemoPage.click();
	}

	// Click the "Get Free Demo" submit button
	public void clickSubmitButton() {
	    getFreeDemoSubmitButtonDemoPage.click();
	}

	// Get any error message displayed after submission
	public String getErrorText() {
	    return errorTextAfterClickingGetFreeDemo.getText();
	}

	
	
	
	
}
