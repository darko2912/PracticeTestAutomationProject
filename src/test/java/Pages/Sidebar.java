package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sidebar extends BaseTest {
    public Sidebar(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "menu-primary-items")
    public WebElement sidebarMenu;

    @FindBy(id = "menu-item-43")
    public WebElement homeLink;

    @FindBy(id = "menu-item-20")
    public WebElement practiceLink;

    @FindBy(id = "menu-item-21")
    public WebElement coursesLink;

    @FindBy(id = "menu-item-19")
    public WebElement blogLink;

    @FindBy(id = "menu-item-18")
    public WebElement contactLink;

    @FindBy(className = "custom-logo-link")
    public WebElement logoLink;

    //--------------------------------------

    public void clickOnHomeLink(){
        homeLink.click();
    }

    public void clickOnPracticeLink(){
        practiceLink.click();
    }

    public void clickOnCoursesLink(){
        coursesLink.click();
    }

    public void clickOnBlogLink(){
        blogLink.click();
    }

    public void clickOnContactLink(){
        contactLink.click();
    }

    public void clickOnLogoLink(){
        logoLink.click();
    }
}
