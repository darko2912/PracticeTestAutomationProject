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
import static Helpers.Data.contactTitle;
import static Helpers.URLs.*;
import static Helpers.URLs.contactURL;

public class BlogpageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.navigate().to(homepageURL);

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
    public void userCanRedirectedFromBlogToHomeUsingSidebarMenu(){
        sidebar.clickOnBlogLink();
        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnHomeLink();

        Assert.assertTrue(sidebar.homeLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), homepageURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), homepageTitle);
    }

    @Test(priority = 20, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromBlogToCoursesUsingSidebarMenu(){
        sidebar.clickOnBlogLink();
        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnCoursesLink();

        Assert.assertTrue(sidebar.coursesLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), coursesURL);
        Assert.assertEquals(coursesPage.coursesTitle.getText(), coursesTitle);
    }

    @Test(priority = 30, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromBlogToPracticeUsingSidebarMenu(){
        sidebar.clickOnBlogLink();
        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnPracticeLink();

        Assert.assertTrue(sidebar.practiceLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), practiceURL);
        Assert.assertEquals(practicePage.practiceTitle.getText(), practiceTitle);
    }

    @Test(priority = 40, retryAnalyzer = RetryAnalyzer.class)
    public void userCanRedirectedFromBlogToContactUsingSidebarMenu(){
        sidebar.clickOnBlogLink();
        Assert.assertTrue(sidebar.blogLink.getAttribute("class").contains("current-menu-item"));
        sidebar.clickOnContactLink();

        Assert.assertTrue(sidebar.contactLink.getAttribute("class").contains("current-menu-item"));
        Assert.assertEquals(driver.getCurrentUrl(), contactURL);
        Assert.assertEquals(contactPage.contactTitle.getText(), contactTitle);
    }

    @Test (priority = 50, retryAnalyzer = RetryAnalyzer.class)
    public void userCanScrollPagesUsingNextPreviousButtons(){
        sidebar.clickOnBlogLink();
        blogPage.scrollingPagesWithNextPreviousButtons();
    }

    @Test (priority = 60, retryAnalyzer = RetryAnalyzer.class)
    public void userCanScrollPagesUsingNumbering(){
        sidebar.clickOnBlogLink();
        blogPage.scrollingPagesWithNumberingPageButtons();
    }

    @Test (priority = 70, retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatTenArticlesShownOnFirstAndSecondPages() {
        sidebar.clickOnBlogLink();
        scrollToElement(blogPage.selectedPage);

        Assert.assertEquals(blogPage.selectedPage.getText(), "1");
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "10");

        blogPage.clickOnNextButton();
        scrollToElement(blogPage.selectedPage);

        Assert.assertEquals(blogPage.selectedPage.getText(), "2");
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "10");
    }

    @Test (priority = 80, retryAnalyzer = RetryAnalyzer.class)
    public void userCanOpenBlogWithClickingOnBlogTitle(){
        sidebar.clickOnBlogLink();

        String nameOfBlog1a = page1Blog3;
        blogPage.clickOnBlogTitle(nameOfBlog1a);
        Assert.assertEquals(driver.getCurrentUrl(), page1Blog3URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog1a);
        driver.navigate().back();

        String nameOfBlog1b = page1Blog6;
        blogPage.clickOnBlogTitle(nameOfBlog1b);
        Assert.assertEquals(driver.getCurrentUrl(), page1Blog6URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog1b);
        driver.navigate().back();

        String nameOfBlog1c = page1Blog8;
        blogPage.clickOnBlogTitle(nameOfBlog1c);
        Assert.assertEquals(driver.getCurrentUrl(), page1Blog8URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog1c);
        driver.navigate().back();

        scrollToElement(blogPage.selectedPage);
        blogPage.clickOnNextButton();

        String nameOfBlog2a = page2Blog7;
        blogPage.clickOnBlogTitle(nameOfBlog2a);
        Assert.assertEquals(driver.getCurrentUrl(), page2Blog7URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog2a);
        driver.navigate().back();

        String nameOfBlog2b = page2Blog4;
        blogPage.clickOnBlogTitle(nameOfBlog2b);
        Assert.assertEquals(driver.getCurrentUrl(), page2Blog4URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog2b);
        driver.navigate().back();

        String nameOfBlog2c = page2Blog2;
        blogPage.clickOnBlogTitle(nameOfBlog2c);
        Assert.assertEquals(driver.getCurrentUrl(), page2Blog2URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog2c);
        driver.navigate().back();

        scrollToElement(blogPage.selectedPage);
        blogPage.clickOnNextButton();

        String nameOfBlog3 = page3Blog1;
        blogPage.clickOnBlogTitle(nameOfBlog3);
        Assert.assertEquals(driver.getCurrentUrl(), page3Blog1URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), nameOfBlog3);
    }

    @Test (priority = 90, retryAnalyzer = RetryAnalyzer.class)
    public void verifyThatEveryArticleHaveReadMoreButton(){
        sidebar.clickOnBlogLink();

        Assert.assertEquals(blogPage.selectedPage.getText(), "1");
        Assert.assertEquals(String.valueOf(blogPage.readMoreButtons.size()), String.valueOf(blogPage.articles.size()));

        scrollToElement(blogPage.selectedPage);
        blogPage.clickOnNextButton();

        Assert.assertEquals(blogPage.selectedPage.getText(), "2");
        Assert.assertEquals(String.valueOf(blogPage.readMoreButtons.size()), String.valueOf(blogPage.articles.size()));

        scrollToElement(blogPage.selectedPage);
        blogPage.clickOnNextButton();

        Assert.assertEquals(blogPage.selectedPage.getText(), "3");
        Assert.assertEquals(String.valueOf(blogPage.readMoreButtons.size()), String.valueOf(blogPage.articles.size()));
    }

    @Test (priority = 100, retryAnalyzer = RetryAnalyzer.class)
    public void userCanOpenBlogWithClickingOnReadMoreButton(){
        sidebar.clickOnBlogLink();
        Assert.assertEquals(blogPage.selectedPage.getText(), "1");
        Assert.assertEquals(String.valueOf(blogPage.readMoreButtons.size()), "10");

        blogPage.clickOnReadMoreButton(3);
        Assert.assertEquals(driver.getCurrentUrl(), page1Blog3URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), page1Blog3);
        driver.navigate().back();

        blogPage.clickOnReadMoreButton(7);
        Assert.assertEquals(driver.getCurrentUrl(), page1Blog7URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), page1Blog7);
        driver.navigate().back();

        scrollToElement(blogPage.selectedPage);
        blogPage.clickOnNextButton();
        Assert.assertEquals(blogPage.selectedPage.getText(), "2");
        Assert.assertEquals(String.valueOf(blogPage.readMoreButtons.size()), "10");

        blogPage.clickOnReadMoreButton(2);
        Assert.assertEquals(driver.getCurrentUrl(), page2Blog2URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), page2Blog2);
        driver.navigate().back();

        blogPage.clickOnReadMoreButton(6);
        Assert.assertEquals(driver.getCurrentUrl(), page2Blog6URL);
        Assert.assertEquals(String.valueOf(blogPage.articles.size()), "1");
        Assert.assertEquals(blogPage.blogTitle.getText(), page2Blog6);
        driver.navigate().back();
    }

    @AfterMethod
    public void tearDownTest(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
