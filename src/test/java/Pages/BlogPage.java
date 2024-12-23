package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class BlogPage extends BaseTest {
    public BlogPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "post-title")
    public WebElement blogTitle;

    @FindBy(css = ".prev.page-numbers")
    public WebElement previousButton;

    @FindBy(css = ".next.page-numbers")
    public WebElement nextButton;

    @FindBy(css = ".page-numbers.current")
    public WebElement selectedPage;

    @FindBy(className = "page-numbers")
    public List<WebElement> pageNumbers;

    @FindBy(className = "post-content")
    public List<WebElement> articles;

    @FindBy(className = "more-link")
    public List<WebElement> readMoreButtons;

    @FindBy(className = "post-title")
    public List<WebElement> blogsTitle;

    //---------------------------------

    public void clickOnPreviousButton(){
        previousButton.click();
    }

    public void clickOnNextButton(){
        nextButton.click();
    }
    //Method for visibility of next/prevoius button
    public boolean buttonIsVisible(String buttonName){
        boolean isPresent = false;
        if (buttonName.equalsIgnoreCase("next")){
            try {
                isPresent=nextButton.isDisplayed();
            } catch (Exception e) {

            }
        } else if (buttonName.equalsIgnoreCase("previous")) {
            try {
                isPresent=previousButton.isDisplayed();
            } catch (Exception e) {

            }
        }
        return isPresent;
    }
    //Method for scrolling pages using next/previous buttons with assertations
    public void scrollingPagesWithNextPreviousButtons(){
        int a = 1;
        do {
            scrollToElement(nextButton);
            Assert.assertEquals(selectedPage.getText(), String.valueOf(a));
            clickOnNextButton();
            a++;
        } while (buttonIsVisible("next"));
        do {
            scrollToElement(previousButton);
            Assert.assertEquals(selectedPage.getText(), String.valueOf(a));
            clickOnPreviousButton();
            a--;
        }while (buttonIsVisible("previous"));
        Assert.assertEquals(selectedPage.getText(), String.valueOf(a));
    }

    public void clickOnNumberingPageButton(int page){
        String pageNumber = String.valueOf(page);
        for (int i=0; i<pageNumbers.size(); i++){
            if (pageNumber.equalsIgnoreCase(pageNumbers.get(i).getText())){
                pageNumbers.get(i).click();
                break;
            }
        }
    }
    //Method for scrolling pages using numbering page buttons with assertations
    public void scrollingPagesWithNumberingPageButtons(){
        int a=1;
        do {
            scrollToElement(selectedPage);
            Assert.assertEquals(selectedPage.getText(), String.valueOf(a));
            clickOnNumberingPageButton(a+1);
            a++;
        }while (!selectedPage.getText().equalsIgnoreCase("3"));
        do {
            scrollToElement(selectedPage);
            Assert.assertEquals(selectedPage.getText(), String.valueOf(a));
            clickOnNumberingPageButton(a-1);
            a--;
        }while (!selectedPage.getText().equalsIgnoreCase("1"));
        Assert.assertEquals(selectedPage.getText(), String.valueOf(a));
    }

    public void clickOnBlogTitle(String blogTitle){
        for (int i=0; i<blogsTitle.size(); i++){
            if (blogsTitle.get(i).getText().equalsIgnoreCase(blogTitle)){
                blogsTitle.get(i).click();
                break;
            }
        }
    }

    public void clickOnReadMoreButton(int numberBetweenOneAndTen){
        scrollToElement(readMoreButtons.get(numberBetweenOneAndTen-1));
        readMoreButtons.get(numberBetweenOneAndTen-1).click();
    }
}
