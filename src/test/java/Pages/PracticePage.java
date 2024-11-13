package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticePage extends BaseTest {
    public PracticePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "post-title")
    public WebElement practiceTitle;

    @FindBy(linkText = "Test Login Page")
    public WebElement testLoginPageLink;

    @FindBy(linkText = "Test Exceptions")
    public WebElement testExceptionsLink;

    //--------------------------------------

    public void clickOnTestLoginPage(){
        testLoginPageLink.click();
    }

    public void clickOnTestExceptions(){
        testExceptionsLink.click();
    }
}
