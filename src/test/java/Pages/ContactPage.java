package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends BaseTest {
    public ContactPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "post-title")
    public WebElement contactTitle;
}
