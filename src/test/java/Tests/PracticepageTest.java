package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Base.RetryAnalyzer;
import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static Helpers.Data.*;
import static Helpers.Data.contactTitle;
import static Helpers.URLs.*;

public class PracticepageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.navigate().to(homepageURL);

        excelReader = new ExcelReader("TestData.xlsx");
        homepagePage = new HomepagePage();
        practicePage = new PracticePage();
        sidebar = new Sidebar();
        coursesPage = new CoursesPage();
        blogPage = new BlogPage();
        contactPage = new ContactPage();
        loginPage = new LoginPage();
        exceptionsPage = new ExceptionsPage();
    }

    @Test(priority = 10, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromPracticeToHomeUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnHomeLink();

        Assert.assertTrue(sidebar.homeLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), homepageTitle);
    }

    @Test(priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromPracticeToCoursesUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnCoursesLink();

        Assert.assertTrue(sidebar.coursesLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        Assert.assertEquals(coursesPage.coursesTitle.getText(), coursesTitle);
    }

    @Test(priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromPracticeToBlogUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnBlogLink();

        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
        Assert.assertEquals(blogPage.blogTitle.getText(), blogTitle);
    }

    @Test(priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromPracticeToContactUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnContactLink();

        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), contactURL);
        Assert.assertEquals(contactPage.contactTitle.getText(), contactTitle);
    }

    @Test(priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void testLoginPageLinkWorksProperly(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();

        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertTrue(loginPage.loginFormIsVisible());
    }

    @Test(priority = 60, retryAnalyzer = RetryAnalyzer.class)
    public void testExceptionsLinkWorksProperly(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestExceptions();

        Assert.assertEquals(driver.getCurrentUrl(), exceptionsURL);
        Assert.assertTrue(exceptionsPage.foodListIsVisible());
    }

    @AfterMethod
    public void tearDownTest(){
        driver.manage().deleteAllCookies();
        //driver.quit();
    }
}
