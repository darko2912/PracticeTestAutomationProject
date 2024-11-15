package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(id = "error")
    public WebElement errorMessage;

    @FindBy(id = "form")
    public WebElement loginForm;

    //----------------------------------

    public void inputUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnSubmitButton(){
        submitButton.click();
    }
    //Method for the visibility login form
    public boolean loginFormIsVisible(){
        boolean isPresent = false;
        try {
            isPresent = loginForm.isDisplayed();
        } catch (Exception e) {

        }
        return isPresent;
    }
}
