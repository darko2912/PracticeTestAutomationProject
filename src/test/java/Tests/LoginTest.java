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
import static Helpers.URLs.*;

public class LoginTest extends BaseTest {

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
        loginPage = new LoginPage();
        loggedPage = new LoggedPage();
        coursesPage = new CoursesPage();
        blogPage = new BlogPage();
        contactPage = new ContactPage();
    }

    @Test(priority = 10, retryAnalyzer = RetryAnalyzer.class)
    public void userCanLogin(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnSubmitButton();

        Assert.assertEquals(driver.getCurrentUrl(), loggedInURL);
        Assert.assertTrue(loggedPage.logoutButtonIsVisible());
        Assert.assertEquals(loggedPage.loggedTitle.getText(), loggedMessage);
    }

    @Test(priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCanLogout(){
        logIn();
        loggedPage.clickOnLogoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertFalse(loggedPage.logoutButtonIsVisible());
        Assert.assertTrue(loginPage.submitButton.isDisplayed());
    }

    @Test(priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLoginWithEmptyFields(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickOnSubmitButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertFalse(loggedPage.logoutButtonIsVisible());
        Assert.assertEquals(loginPage.errorMessage.getText(), usernameIsInvalid);
    }

    @Test(priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLoginWithEmptyPasswordField(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword("");
        loginPage.clickOnSubmitButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertFalse(loggedPage.logoutButtonIsVisible());
        Assert.assertEquals(loginPage.errorMessage.getText(), passwordIsInvalid);
    }

    @Test(priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLoginWithEmptyUsernameField(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        loginPage.inputUsername("");
        loginPage.inputPassword(validPassword);
        loginPage.clickOnSubmitButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginURL);
        Assert.assertFalse(loggedPage.logoutButtonIsVisible());
        Assert.assertEquals(loginPage.errorMessage.getText(), usernameIsInvalid);
    }

    @Test(priority = 60, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLoginWithInvalidUsername(){
        for (int i=1; i<= excelReader.getLastRow("Login"); i++){
            String invalidUsername = excelReader.getStringData("Login", i,2);
            sidebar.clickOnPracticeLink();
            practicePage.clickOnTestLoginPage();
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnSubmitButton();

            Assert.assertEquals(driver.getCurrentUrl(), loginURL);
            Assert.assertFalse(loggedPage.logoutButtonIsVisible());
            Assert.assertEquals(loginPage.errorMessage.getText(), usernameIsInvalid);

            driver.manage().deleteAllCookies();
        }
    }

    @Test(priority = 70, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLoginWithInvalidPassword(){
        for (int i=1; i<= excelReader.getLastRow("Login"); i++){
            String invalidPassword = excelReader.getStringData("Login", i,3);
            sidebar.clickOnPracticeLink();
            practicePage.clickOnTestLoginPage();
            loginPage.inputUsername(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnSubmitButton();

            Assert.assertEquals(driver.getCurrentUrl(), loginURL);
            Assert.assertFalse(loggedPage.logoutButtonIsVisible());
            Assert.assertEquals(loginPage.errorMessage.getText(), passwordIsInvalid);

            driver.manage().deleteAllCookies();
        }
    }

    @Test(priority = 80, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotLoginWithInvalidUsernameAndPassword(){
        for (int i=1; i<= excelReader.getLastRow("Login"); i++){
            String invalidUsername = excelReader.getStringData("Login", i,2);
            String invalidPassword = excelReader.getStringData("Login", i,3);
            sidebar.clickOnPracticeLink();
            practicePage.clickOnTestLoginPage();
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnSubmitButton();

            Assert.assertEquals(driver.getCurrentUrl(), loginURL);
            Assert.assertFalse(loggedPage.logoutButtonIsVisible());
            Assert.assertEquals(loginPage.errorMessage.getText(), usernameIsInvalid);

            driver.manage().deleteAllCookies();
        }
    }

    @Test(priority = 90, retryAnalyzer = RetryAnalyzer.class)
    public void logoLinkSuccessfullyRedirectedFromLoginPageToHomepage(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        sidebar.clickOnLogoLink();

        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertEquals(homepagePage.homepageTitle.getText(), homepageTitle);
    }

    @Test(priority = 100, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromLoginPageToHomeUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        sidebar.clickOnHomeLink();

        Assert.assertTrue(sidebar.homeLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertEquals(homepagePage.homepageTitle.getText(), homepageTitle);
    }

    @Test(priority = 110, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromLoginPageToPracticeUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        sidebar.clickOnPracticeLink();

        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), practiceURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), practiceTitle);
    }

    @Test(priority = 120, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromLoginPageToCoursesUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        sidebar.clickOnCoursesLink();

        Assert.assertTrue(sidebar.coursesLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        Assert.assertEquals(coursesPage.coursesTitle.getText(), coursesTitle);
    }

    @Test(priority = 130, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromLoginPageToBlogUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        sidebar.clickOnBlogLink();

        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
    }

    @Test(priority = 140, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromLoginPageToContactUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();
        practicePage.clickOnTestLoginPage();
        sidebar.clickOnContactLink();

        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), contactURL);
        Assert.assertEquals(contactPage.contactTitle.getText(), contactTitle);
    }

    @AfterMethod
    public void tearDownTest(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}