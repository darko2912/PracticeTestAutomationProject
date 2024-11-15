package Base;

import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;

import static Helpers.Data.validPassword;
import static Helpers.Data.validUsername;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static ExcelReader excelReader;
    public HomepagePage homepagePage;
    public Sidebar sidebar;
    public PracticePage practicePage;
    public LoginPage loginPage;
    public LoggedPage loggedPage;
    public CoursesPage coursesPage;
    public BlogPage blogPage;
    public ContactPage contactPage;

    @BeforeClass
    public void setUp() throws IOException {
        excelReader = new ExcelReader("TestData.xlsx");
        homepagePage = new HomepagePage();
        practicePage = new PracticePage();
        sidebar = new Sidebar();
        loginPage = new LoginPage();
        loggedPage = new LoggedPage();
        coursesPage = new CoursesPage();
        blogPage = new BlogPage();
        contactPage = new ContactPage();

    }

    @AfterClass
    public void tearDownProcess(){
        //driver.quit();
    }

    //Method for scrolling to the desired element.
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    //Method for setting focus on a specific tab.
    public void switchTab(int tab){
        ArrayList<String> listTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listTab.get(tab));
    }
    //Method to delete a tab and return to the first tab.
    public void deleteTab(){
        driver.close();
        switchTab(0);
    }
    //Method to waiting for the visibility of the element.
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    //Methode to waiting for the clickability of the element.
    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    //Method for js click
    public void clickOnElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }
    //Methode for login with valid credentials
    public void logIn(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnSubmitButton();
    }
}
