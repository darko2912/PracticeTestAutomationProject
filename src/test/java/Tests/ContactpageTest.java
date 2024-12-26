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

public class ContactpageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

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
    public void userCanRedirectedFromContactToHomeUsingSidebarMenu(){
        sidebar.clickOnContactLink();
        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnHomeLink();

        Assert.assertTrue(sidebar.homeLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), homepageTitle);
    }

    @Test(priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromContactToPracticeUsingSidebarMenu(){
        sidebar.clickOnContactLink();
        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnPracticeLink();

        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), practiceURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), practiceTitle);
    }

    @Test(priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromContactToCourseUsingSidebarMenu(){
        sidebar.clickOnContactLink();
        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnCoursesLink();

        Assert.assertTrue(sidebar.coursesLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        Assert.assertEquals(coursesPage.coursesTitle.getText(), coursesTitle);
    }

    @Test(priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromContactToBlogUsingSidebarMenu(){
        sidebar.clickOnContactLink();
        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnBlogLink();

        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), blogURL);
    }

    @Test(priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void userCanSubmitContactFormWithValidCredentials(){
        sidebar.clickOnContactLink();
        contactPage.inputFirstName(validFirstName);
        contactPage.inputLastName(validLastName);
        contactPage.inputEmail(validEmailContact);
        contactPage.inputComment(comment);
        contactPage.selectRechaptchaCheckbox();
        contactPage.clickOnSubmitButton();

        Assert.assertTrue(contactPage.confirmationMessage.isDisplayed());
        Assert.assertEquals(contactPage.confirmationMessage.getText(), confirmationMessage);
    }

    @Test(priority = 60, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubmitContactFormWithEmptyFirstName(){
        sidebar.clickOnContactLink();
        contactPage.inputLastName(validLastName);
        contactPage.inputEmail(validEmailContact);
        contactPage.inputComment(comment);
        //contactPage.selectRechaptchaCheckbox();
        contactPage.clickOnSubmitButton();

        Assert.assertTrue(contactPage.errorFirstNameMessage.isDisplayed());
        Assert.assertEquals(contactPage.errorFirstNameMessage.getText(), requiredField);
        Assert.assertTrue(contactPage.firstNameField.getAttribute("aria-invalid").contains("true"));
    }

    @Test(priority = 70, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubmitContactFormWithEmptyLastName(){
        sidebar.clickOnContactLink();
        contactPage.inputFirstName(validFirstName);
        contactPage.inputEmail(validEmailContact);
        contactPage.inputComment(comment);
        //contactPage.selectRechaptchaCheckbox();
        contactPage.clickOnSubmitButton();

        Assert.assertTrue(contactPage.errorLastNameMessage.isDisplayed());
        Assert.assertEquals(contactPage.errorLastNameMessage.getText(), requiredField);
        Assert.assertTrue(contactPage.lastNameField.getAttribute("aria-invalid").contains("true"));
    }

    @Test(priority = 80, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubmitContactFormWithEmptyEmail(){
        sidebar.clickOnContactLink();
        contactPage.inputFirstName(validFirstName);
        contactPage.inputLastName(validLastName);
        contactPage.inputComment(comment);
        //contactPage.selectRechaptchaCheckbox();
        contactPage.clickOnSubmitButton();

        Assert.assertTrue(contactPage.errorEmailMessage.isDisplayed());
        Assert.assertEquals(contactPage.errorEmailMessage.getText(), requiredField);
        Assert.assertTrue(contactPage.emailField.getAttribute("aria-invalid").contains("true"));
    }

    @Test(priority = 90, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubmitContactFormWithEmptyComment(){
        sidebar.clickOnContactLink();
        contactPage.inputFirstName(validFirstName);
        contactPage.inputLastName(validLastName);
        contactPage.inputEmail(validEmailContact);
        //contactPage.selectRechaptchaCheckbox();
        contactPage.clickOnSubmitButton();

        Assert.assertTrue(contactPage.errorCommentMessage.isDisplayed());
        Assert.assertEquals(contactPage.errorCommentMessage.getText(), requiredField);
        Assert.assertTrue(contactPage.commentField.getAttribute("aria-invalid").contains("true"));
    }

    @Test(priority = 100, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubmitContactFormWithUnselectedRecaptcha(){
        sidebar.clickOnContactLink();
        contactPage.inputFirstName(validFirstName);
        contactPage.inputLastName(validLastName);
        contactPage.inputEmail(validEmailContact);
        contactPage.inputComment(comment);
        contactPage.clickOnSubmitButton();

        Assert.assertTrue(contactPage.errorRecaptchaMessage.isDisplayed());
        Assert.assertEquals(contactPage.errorRecaptchaMessage.getText(), recaptchaError);
    }

    @Test(priority = 110, retryAnalyzer = RetryAnalyzer.class)
    public void userCannotSubmitContactFormWithInvalidEmail() {
        sidebar.clickOnContactLink();
        for (int i=1; i <= excelReader.getLastRow("Contact"); i++){
            String invalidEmail = excelReader.getStringData("Contact", i, 4);
            contactPage.inputFirstName(validFirstName);
            contactPage.inputLastName(validLastName);
            contactPage.inputEmail(invalidEmail);
            contactPage.inputComment(comment);
            //contactPage.selectRechaptchaCheckbox();
            contactPage.clickOnSubmitButton();

            Assert.assertTrue(contactPage.errorEmailMessage.isDisplayed());
            Assert.assertEquals(contactPage.errorEmailMessage.getText(), errorEmail);
            Assert.assertTrue(contactPage.emailField.getAttribute("aria-invalid").contains("true"));
        }
    }

    @Test(priority = 120, retryAnalyzer = RetryAnalyzer.class)
    public void userGetSuggestionMessageForEmailSpellingMistake() {
        sidebar.clickOnContactLink();
        for (int i=1; i <= excelReader.getLastRow("Contact"); i++){
            String invalidEmail = excelReader.getStringData("Contact", i, 5);
            contactPage.inputFirstName(validFirstName);
            contactPage.inputLastName(validLastName);
            contactPage.inputEmail(invalidEmail);
            contactPage.inputComment(comment);
            //contactPage.selectRechaptchaCheckbox();

            Assert.assertTrue(contactPage.emailSuggestionMessage.isDisplayed());
            Assert.assertTrue(contactPage.emailSuggestionMessage.getText().contains(emailSuggestion));
        }
    }

    @AfterMethod
    public void tearDownTest(){
        driver.manage().deleteAllCookies();
        //driver.quit();
    }
}
