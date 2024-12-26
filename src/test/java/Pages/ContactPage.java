package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends BaseTest {
    public ContactPage(){
        PageFactory.initElements(driver,this);
    }

    //Finding iframe on HTML page
    WebElement iframe;
    public WebElement getIframe(){
        return driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']"));
    }

    @FindBy(className = "post-title")
    public WebElement contactTitle;

    @FindBy(id = "wpforms-161-field_0")
    public WebElement firstNameField;

    @FindBy(id = "wpforms-161-field_0-last")
    public WebElement lastNameField;

    @FindBy(id = "wpforms-161-field_1")
    public WebElement emailField;

    @FindBy(id = "wpforms-161-field_2")
    public WebElement commentField;

    @FindBy(className = "recaptcha-checkbox-borderAnimation")
    public WebElement recaptchaCheckbox;

    @FindBy(id = "wpforms-submit-161")
    public WebElement submitButton;

    @FindBy(id = "wpforms-confirmation-161")
    public WebElement confirmationMessage;

    @FindBy(id = "wpforms-161-field_0-error")
    public WebElement errorFirstNameMessage;

    @FindBy(id = "wpforms-161-field_0-last-error")
    public WebElement errorLastNameMessage;

    @FindBy(id = "wpforms-161-field_1-error")
    public WebElement errorEmailMessage;

    @FindBy(id = "wpforms-161-field_2-error")
    public WebElement errorCommentMessage;

    @FindBy(id = "wpforms-field_recaptcha-error")
    public WebElement errorRecaptchaMessage;

    @FindBy(id = "wpforms-161-field_1_suggestion")
    public WebElement emailSuggestionMessage;

    //--------------------------------

    public void inputFirstName(String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void inputEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void inputComment(String comment){
        commentField.clear();
        commentField.sendKeys(comment);
    }

    public void selectRechaptchaCheckbox(){
        driver.switchTo().frame(getIframe()); //Switch focus to the desire iframe
        clickOnElement(recaptchaCheckbox);
        driver.switchTo().defaultContent(); //Switch focus to the main content of page
    }

    public void clickOnSubmitButton(){
        submitButton.click();
    }

}
