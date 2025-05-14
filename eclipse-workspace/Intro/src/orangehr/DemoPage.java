package orangehr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoPage extends BasePageObject {

    // More robust locators as By objects
    private static final By FULL_NAME_INPUT = By.id("Form_getForm_FullName");
    private static final By PHONE_NUMBER_INPUT = By.id("Form_getForm_Contact");
    private static final By EMAIL_INPUT = By.id("Form_getForm_Email");
    private static final By COMPANY_NAME_INPUT = By.id("Form_getForm_CompanyName");
    private static final By COUNTRY_DROPDOWN = By.name("Country");
    private static final By EMPLOYEES_DROPDOWN = By.name("NoOfEmployees");
    private static final By SUBMIT_BUTTON = By.id("Form_getForm_action_submitForm");
    private static final By FORM_TITLE = By.xpath("//h4[@class='form-title']");
    
    // Retain original FindBy elements
    @FindBy(xpath="//input[@id='Form_getForm_FullName']")
    WebElement fullNameDemoPage;
    
    @FindBy(xpath="//input[@id='Form_getForm_Contact']")
    WebElement phoneNumberDemoPage;
    
    @FindBy(xpath="//input[@id='Form_getForm_Email']")
    WebElement businessEmailDemoPage;
    
    @FindBy(xpath="//input[@id='Form_getForm_CompanyName']")
    WebElement companyNameDemoPage;
    
    @FindBy(xpath="//select[@name='Country']")
    WebElement countryDropDownDemoPage;
    
    @FindBy(xpath="//select[@name='NoOfEmployees']")
    WebElement numberOfEmployeeDemoPage;
    
    @FindBy(xpath="//option[@value='Afghanistan']")
    WebElement countryselectionFromDropDownDemoPage;
    
    @FindBy(xpath="//option[@value='< 10']")
    WebElement employeeSelectionFromDropDownDemoPage;
    
    @FindBy(xpath="//input[@id='Form_getForm_action_submitForm']")
    WebElement getFreeDemoSubmitButtonDemoPage;
    
    @FindBy(xpath="//h4[@class='form-title']")
    WebElement errorTextAfterClickingGetFreeDemo;
    
    public DemoPage(WebDriver driver) {
        super(driver);
    }
    
    // Enhanced methods using our base page functionality
    public void enterFullName(String fullName) {
        WebElement element = findWithRetry(d -> d.findElement(FULL_NAME_INPUT));
        sendKeysWithRetry(element, fullName, 3);
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement element = findWithRetry(d -> d.findElement(PHONE_NUMBER_INPUT));
        sendKeysWithRetry(element, phoneNumber, 3);
    }

    public void enterBusinessEmail(String email) {
        WebElement element = findWithRetry(d -> d.findElement(EMAIL_INPUT));
        sendKeysWithRetry(element, email, 3);
    }

    public void enterCompanyName(String companyName) {
        WebElement element = findWithRetry(d -> d.findElement(COMPANY_NAME_INPUT));
        sendKeysWithRetry(element, companyName, 3);
    }

    public void selectCountry() {
        WebElement countryDropdown = findWithRetry(d -> d.findElement(COUNTRY_DROPDOWN));
        clickWithRetry(countryDropdown, 3);
        
        WebElement option = findWithRetry(d -> d.findElement(By.xpath("//option[@value='Afghanistan']")));
        clickWithRetry(option, 3);
    }

    public void selectNumberOfEmployees() {
        WebElement employeesDropdown = findWithRetry(d -> d.findElement(EMPLOYEES_DROPDOWN));
        clickWithRetry(employeesDropdown, 3);
        
        WebElement option = findWithRetry(d -> d.findElement(By.xpath("//option[@value='< 10']")));
        clickWithRetry(option, 3);
    }

    public void clickSubmitButton() {
        WebElement submitButton = findWithRetry(d -> d.findElement(SUBMIT_BUTTON));
        clickWithRetry(submitButton, 3);
        waitForPageLoadComplete();
    }

    public String getErrorText() {
        WebElement formTitle = findWithRetry(d -> d.findElement(FORM_TITLE));
        return getTextWithRetry(formTitle, 3);
    }
}