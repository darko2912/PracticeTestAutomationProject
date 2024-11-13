package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesPage extends BaseTest {

    public CoursesPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "post-title")
    public WebElement coursesTitle;
}
