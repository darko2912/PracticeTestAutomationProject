package Helpers;

import Base.BaseTest;

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
    public static final String confirmationMessage = "Thanks for contacting us! We will be in touch with you shortly.";
    //Error messages
    public static final String usernameIsInvalid = "Your username is invalid!";
    public static final String passwordIsInvalid = "Your password is invalid!";
    public static final String requiredField = "This field is required.";
    public static final String invalidEmailMessage = "This value should be a valid email.";
    public static final String recaptchaError = "Google reCAPTCHA verification failed, please try again later.";
    public static final String errorEmail = "Please enter a valid email address.";
    public static final String emailSuggestion = "Did you mean ";
    //Title of pages
    public static final String homepageTitle = "Hello";
    public static final String practiceTitle = "Practice";
    public static final String coursesTitle = "Courses";
    public static final String contactTitle = "Contact";
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
    //Title of blogs
    public static final String page1Blog1 = "Unlock Your Future: Selenium WebDriver Career Launcher Part 6";
    public static final String page1Blog2 = "Unlock Your Future: Selenium WebDriver Career Launcher Part 5";
    public static final String page1Blog3 = "Unlock Your Future: Selenium WebDriver Career Launcher Part 4";
    public static final String page1Blog4 = "Unlock Your Future: Selenium WebDriver Career Launcher Part 3";
    public static final String page1Blog5 = "Unlock Your Future: Selenium WebDriver Career Launcher Part 2";
    public static final String page1Blog6 = "Unlock Your Future: Selenium WebDriver Career Launcher Part 1";
    public static final String page1Blog7 = "Headless Browser Testing with Selenium: Elevate Your Expertise";
    public static final String page1Blog8 = "Unlock the Potential: Mastering the Test Automation Pyramid";
    public static final String page1Blog9 = "Unusual Uses for Selenium WebDriver";
    public static final String page1Blog10 = "Starting with Selenium WebDriver and Java: An Overview for Beginners";
    public static final String page2Blog1 = "How to deal with Element is not clickable at point exception";
    public static final String page2Blog2 = "Hard and Soft Assertions in test automation";
    public static final String page2Blog3 = "How to find cheap flights with Selenium to save time";
    public static final String page2Blog4 = "Best Books For Software Testers";
    public static final String page2Blog5 = "Stop automating everything with Selenium";
    public static final String page2Blog6 = "DevOps 101 for Testers";
    public static final String page2Blog7 = "Postman RestAPI Testing";
    public static final String page2Blog8 = "Selenium WebDriver and Browser alerts";
    public static final String page2Blog9 = "Checkboxes and radio buttons with Selenium";
    public static final String page2Blog10 = "Why XPath Locator strategy?";
    public static final String page3Blog1 = "Selenium Grid | Jenkins";
    //Valid credentials for contact form
    public static final String validFirstName = excelReader.getStringData("Contact", 1,0);
    public static final String validLastName = excelReader.getStringData("Contact",1,1);
    public static final String validEmailContact = excelReader.getStringData("Contact",1,2);
    public static final String comment = excelReader.getStringData("Contact", 1,3);
}
