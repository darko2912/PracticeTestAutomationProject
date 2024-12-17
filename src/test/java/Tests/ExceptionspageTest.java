package Tests;

import Base.BaseTest;
import Base.RetryAnalyzer;
import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static Helpers.Data.*;
import static Helpers.URLs.*;

public class ExceptionspageTest extends BaseTest {

    @BeforeMethod
    public void setUpPage(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to(homepageURL);

        homepagePage = new HomepagePage();
        sidebar = new Sidebar();
        practicePage = new PracticePage();
        coursesPage = new CoursesPage();
        blogPage = new BlogPage();
        contactPage = new ContactPage();
        exceptionsPage = new ExceptionsPage();
    }

    @Test (priority = 10, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectFromExceptionPageToHomePage(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        sidebar.clickOnHomeLink();

        Assert.assertTrue(sidebar.homeLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertEquals(homepagePage.homepageTitle.getText(), homepageTitle);
    }

    @Test (priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectFromExceptionPageToPracticePage(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        sidebar.clickOnPracticeLink();

        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), practiceURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), practiceTitle);
    }

    @Test (priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectFromExceptionPageToCoursePage(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        sidebar.clickOnCoursesLink();

        Assert.assertTrue(sidebar.coursesLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        Assert.assertEquals(coursesPage.coursesTitle.getText(), coursesTitle);
    }

    @Test (priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectFromExceptionPageToBlogPage(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        sidebar.clickOnBlogLink();

        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
        Assert.assertEquals(blogPage.blogTitle.getText(), blogTitle);
    }

    @Test (priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectFromExceptionPageToContactPage(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        sidebar.clickOnContactLink();

        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), contactURL);
        Assert.assertEquals(contactPage.contactTitle.getText(), contactTitle);
    }

    @Test (priority = 60, retryAnalyzer = RetryAnalyzer.class)
    public void userCanSuccessfullyRedirectedToHomePageUsingLogoLink(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        sidebar.clickOnLogoLink();

        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertTrue(sidebar.homeLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(homepagePage.homepageTitle.getText(), homepageTitle);
    }

    @Test (priority = 70, retryAnalyzer = RetryAnalyzer.class)
    public void userCanEditFirstRowOfFavoriteFood(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        Assert.assertTrue(exceptionsPage.foodFieldList.get(0).getAttribute("value").contains("Pizza"));
        exceptionsPage.clickOnEditButton();
        exceptionsPage.inputFood("Row 1", "Hamburger");
        exceptionsPage.clickOnSaveButton();

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), savedRow1);
        Assert.assertTrue(exceptionsPage.foodFieldList.get(0).getAttribute("Value").contains("Hamburger"));
    }

    @Test (priority = 80, retryAnalyzer = RetryAnalyzer.class)
    public void userCanAddAnotherRowOfFavoriteFood() throws InterruptedException {
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        exceptionsPage.clickOnAddButton();
        Thread.sleep(5000);
        exceptionsPage.inputFood("row 2","Chocolate");

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), addedRow2);

        waitForVisibility(exceptionsPage.saveButton);
        exceptionsPage.clickOnSaveButton();

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), savedRow2);

    }

    @Test (priority = 90, retryAnalyzer = RetryAnalyzer.class)
    public void userCanEditSecondRowOfFavoriteFood() throws InterruptedException {
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        exceptionsPage.clickOnAddButton();
        Thread.sleep(5000);
        exceptionsPage.inputFood("row 2","Chocolate");

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), addedRow2);

        waitForVisibility(exceptionsPage.saveButton);
        exceptionsPage.clickOnRemoveButton();

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), savedRow2);

        exceptionsPage.clickOnEditButton();
        exceptionsPage.inputFood("row 2", "Spaghetti");

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), savedRow2);
    }

    @Test (priority = 100, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRemoveSecondRowOfFavoriteFood() throws InterruptedException {
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();
        exceptionsPage.clickOnAddButton();
        Thread.sleep(5000);
        exceptionsPage.inputFood("row 2", "Chocolate");

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), addedRow2);

        waitForVisibility(exceptionsPage.saveButton);
        exceptionsPage.clickOnRemoveButton();

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), savedRow2);

        exceptionsPage.clickOnRemoveButton();

        Assert.assertTrue(exceptionsPage.message.isDisplayed());
        Assert.assertEquals(exceptionsPage.message.getText(), removedRow2);
    }

    @AfterMethod
    public void tearDownTest(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
