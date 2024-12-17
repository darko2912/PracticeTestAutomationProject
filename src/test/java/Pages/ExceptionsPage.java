package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExceptionsPage extends BaseTest {
    public ExceptionsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "food_list")
    public WebElement foodList;

    @FindBy(className = "input-field")
    public List<WebElement> foodFieldList;

    @FindBy(id = "edit_btn")
    public WebElement editButton;

    @FindBy(id = "add_btn")
    public WebElement addButton;

    @FindBy(id = "save_btn")
    public WebElement saveButton;

    @FindBy(id = "remove_btn")
    public WebElement removeButton;

    @FindBy(id = "confirmation")
    public WebElement message;

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
    //Method for selecting desire food field
    public void inputFood(String desireField, String foodName){
        if (desireField.equalsIgnoreCase("Row 1")){
            foodFieldList.get(0).clear();
            foodFieldList.get(0).sendKeys(foodName);
        } else if (desireField.equalsIgnoreCase("Row 2")) {
            foodFieldList.get(1).clear();
            foodFieldList.get(1).sendKeys(foodName);
        }
    }

    public void clickOnEditButton(){
        editButton.click();
    }

    public void clickOnAddButton(){
        addButton.click();
    }

    public void clickOnSaveButton(){
        saveButton.click();
    }

    public void clickOnRemoveButton(){
        removeButton.click();
    }
}
