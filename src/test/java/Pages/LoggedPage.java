package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedPage extends BaseTest {

    public LoggedPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Log out")
    public WebElement logoutButton;

    @FindBy(className = "post-title")
    public WebElement loggedTitle;

    //-----------------------------------

    public void clickOnLogoutButton(){
        logoutButton.click();
    }
    //Method for visibility of the logout button on the page.
    public boolean logoutButtonIsVisible(){
        boolean isPresent = false;
        try {
            isPresent = logoutButton.isDisplayed();
        } catch (Exception e) {

        }
        return isPresent;
    }
}
