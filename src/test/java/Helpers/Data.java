package Helpers;

import Base.BaseTest;

import java.util.ArrayList;

public class Data extends BaseTest {
    //Valid credentials for log in
    public static final String validUsername = excelReader.getStringData("login", 1,0);
    public static final String validPassword = excelReader.getStringData("Login", 1,1);
    //Successfully messages
    public static final String loggedMessage = "Logged In Successfully";
    public static final String savedRow1 = "Row 1 was saved";
    public static final String savedRow2 = "Row 2 was saved";
    public static final String addedRow2 = "Row 2 was added";
    public static final String removedRow2 = "Row 2 was removed";
    //Error messages
    public static final String usernameIsInvalid = "Your username is invalid!";
    public static final String passwordIsInvalid = "Your password is invalid!";
    public static final String requiredField = "This field is required.";
    public static final String invalidEmailMessage = "This value should be a valid email.";
    //Title of pages
    public static final String homepageTitle = "Hello";
    public static final String practiceTitle = "Practice";
    public static final String coursesTitle = "Courses";
    public static final String blogTitle = "Unlock Your Future: Selenium WebDriver Career Launcher Part 6";
    public static final String contactTitle = "Contact";
    public static final String confirmTitle = "Confirm you’re not a robot";
    //Valid credentials for signing up for the newsletter
    public static final String validName = excelReader.getStringData("SubscribeNewsletter",1,0);
    public static final String validEmail = excelReader.getStringData("SubscribeNewsletter", 1,1);
    //Name of links located on homepage
    public static final String link1 = "nine courses with over 70,000 students";
    public static final String link2 = "BestSeller XPath course";
    public static final String link3 = "HighestRated Selenium course";
    public static final String link4 = "diverse selection of articles, standalone lectures, tips, and examples";
    public static final String link5 = "practical platform";
    public static final String link6 = "BLOG";
    public static final String link7 = "COURSES";
    public static final String link8 = "privacy policy";
}
