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

public class HomepageTest extends BaseTest {

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
    }

    @Test(priority = 10, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromHomepageToPracticeUsingSidebarMenu(){
        sidebar.clickOnPracticeLink();

        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), practiceURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), practiceTitle);
    }

    @Test(priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromHomepageToCoursesUsingSidebarMenu(){
        sidebar.clickOnCoursesLink();

        Assert.assertTrue(sidebar.coursesLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        Assert.assertEquals(coursesPage.coursesTitle.getText(), coursesTitle);
    }

    @Test(priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromHomepageToBlogUsingSidebarMenu(){
        sidebar.clickOnBlogLink();

        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
    }

    @Test(priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromHomepageToContactUsingSidebarMenu(){
        sidebar.clickOnContactLink();

        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), contactURL);
        Assert.assertEquals(contactPage.contactTitle.getText(), contactTitle);
    }

    @Test(priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void userCanSubscribeOnNewsletter(){
        scrollToElement(homepagePage.newsletterForm);
        homepagePage.inputName(validName);
        homepagePage.inputEmail(validEmail);
        homepagePage.clickOnSubscribeSubmitButton();

        Assert.assertTrue(homepagePage.captchaFormIsVisible());
    }

    @Test(priority = 60, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubscribeOnNewsletterWithEmptyFields(){
        scrollToElement(homepagePage.newsletterForm);
        homepagePage.inputName("");
        homepagePage.inputEmail("");
        homepagePage.clickOnSubscribeSubmitButton();

        Assert.assertFalse(homepagePage.captchaFormIsVisible());
        Assert.assertEquals(homepagePage.errorRequiredMessages.get(0).getText(), requiredField);
        Assert.assertEquals(homepagePage.errorRequiredMessages.get(1).getText(), requiredField);
    }

    @Test(priority = 70, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubscribeOnNewsletterWithInvalidEmail(){
        for (int i=1; i<=excelReader.getLastRow("SubscribeNewsletter"); i++){
            String invalidEmail = excelReader.getStringData("SubscribeNewsletter",i,2);
            scrollToElement(homepagePage.newsletterForm);
            homepagePage.inputName(validName);
            homepagePage.inputEmail(invalidEmail);
            homepagePage.clickOnSubscribeSubmitButton();

            Assert.assertFalse(homepagePage.captchaFormIsVisible());
            Assert.assertEquals(homepagePage.invalidEmailMessage.getText(), invalidEmailMessage);
        }
    }

    @Test(priority = 80, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubscribeOnNewsletterWithInvalidName(){
        for (int i=1; i<=excelReader.getLastRow("SubscribeNewsletter"); i++){
            String invalidName = excelReader.getStringData("SubscribeNewsletter",i,3);
            scrollToElement(homepagePage.newsletterForm);
            homepagePage.inputName(invalidName);
            homepagePage.inputEmail(validEmail);
            homepagePage.clickOnSubscribeSubmitButton();

            Assert.assertFalse(homepagePage.captchaFormIsVisible());
        }
    }

    @Test(priority = 90, retryAnalyzer = RetryAnalyzer.class)
    public void footerIsVisibleOnHomepage(){
        Assert.assertTrue(homepagePage.footerIsVisible());
    }

    @Test(priority = 100, retryAnalyzer = RetryAnalyzer.class)
    public void linksOnHomepageAreClickable(){
        homepagePage.scrollToTheLink(link1);
        homepagePage.clickOnLink(link1);
        switchTab(1);
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        deleteTab();

        homepagePage.scrollToTheLink(link2);
        homepagePage.clickOnLink(link2);
        switchTab(1);
        Assert.assertTrue(driver.getCurrentUrl().contains(udemyXpathURL));
        deleteTab();

        homepagePage.scrollToTheLink(link3);
        homepagePage.clickOnLink(link3);
        switchTab(1);
        Assert.assertTrue(driver.getCurrentUrl().contains(udemySeleniumURL));
        deleteTab();

        homepagePage.scrollToTheLink(link4);
        homepagePage.clickOnLink(link4);
        switchTab(1);
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
        deleteTab();

        homepagePage.scrollToTheLink(link5);
        homepagePage.clickOnLink(link5);
        switchTab(1);
        Assert.assertEquals(driver.getCurrentUrl(), practiceURL);
        deleteTab();

        homepagePage.scrollToTheLink(link6);
        homepagePage.clickOnLink(link6);
        switchTab(1);
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
        deleteTab();

        homepagePage.scrollToTheLink(link7);
        homepagePage.clickOnLink(link7);
        switchTab(1);
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        deleteTab();

        homepagePage.scrollToTheLink(link8);
        homepagePage.clickOnLink(link8);
        switchTab(1);
        Assert.assertEquals(driver.getCurrentUrl(), privacyPolicyURL);
        deleteTab();
    }

    @AfterMethod
    public void tearDownTest(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
