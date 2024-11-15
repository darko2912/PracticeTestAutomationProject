package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExceptionsPage extends BaseTest {
    public ExceptionsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "food_list")
    public WebElement foodList;

    //------------------------------

    //Method for the visibility food list
    public boolean foodListIsVisible(){
        boolean isPresent = false;
        try {
            isPresent = foodList.isDisplayed();
        } catch (Exception e) {

        }
        return isPresent;
    }
}
