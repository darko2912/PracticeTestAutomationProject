package Helpers;

import Base.BaseTest;

public class Data extends BaseTest {
    //Valid credentials for log in
    public static final String validUsername = excelReader.getStringData("login", 1,0);
    public static final String validPassword = excelReader.getStringData("Login", 1,1);
    //Successfully messages
    public static final String loggedMessage = "Logged In Successfully";
    //Error messages
    public static final String usernameIsInvalid = "Your username is invalid!";
    public static final String passwordIsInvalid = "Your password is invalid!";
    //Title of pages
    public static final String homepageTitle = "Hello";
    public static final String practiceTitle = "Practice";
    public static final String coursesTitle = "Courses";
    public static final String blogTitle = "Unlock Your Future: Selenium WebDriver Career Launcher Part 6";
    public static final String contactTitle = "Contact";
}
