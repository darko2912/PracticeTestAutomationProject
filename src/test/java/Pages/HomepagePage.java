package Pages;

import Base.BaseTest;
import Helpers.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomepagePage extends BaseTest {

    public HomepagePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "post-title")
    public WebElement homepageTitle;

    @FindBy(id = "form_first_name_7")
    public WebElement newsletterNameField;

    @FindBy(id = "form_email_7")
    public WebElement newsletterEmailField;

    @FindBy(css = "input[data-automation-id='subscribe-submit-button']")
    public WebElement subscribeSubmitButton;

    @FindBy(id = "mp_form_below_posts7")
    public WebElement newsletterForm;

    @FindBy(id = "mailpoet_captcha_form")
    public WebElement captchaForm;

    @FindBy(className = "parsley-required")
    public List<WebElement> errorRequiredMessages;

    @FindBy(className = "parsley-type")
    public WebElement invalidEmailMessage;

    @FindBy(id = "site-footer")
    public WebElement siteFooter;

    @FindBy(css = "a[rel='noreferrer noopener']")
    public List<WebElement> links;

    //---------------------------------------

    public void inputName(String name){
        newsletterNameField.clear();
        newsletterNameField.sendKeys(name);
    }

    public void inputEmail(String email){
        newsletterEmailField.clear();
        newsletterEmailField.sendKeys(email);
    }

    public void clickOnSubscribeSubmitButton(){
        subscribeSubmitButton.click();
    }
    //Method for visibility the captcha form
    public boolean captchaFormIsVisible(){
        boolean isPresent = false;
        try {
            isPresent = captchaForm.isDisplayed();
        } catch (Exception e) {

        }
        return isPresent;
    }
    //Method for visibility footer
    public boolean footerIsVisible(){
        boolean isPresent = false;
        try {
            isPresent = siteFooter.isDisplayed();
        } catch (Exception e) {

        }
        return isPresent;
    }
    //Method to click on a specific links
    public void clickOnLink(String linkName){
        for (int i=0; i<links.size(); i++){
            if (links.get(i).getText().equals(linkName)){
                    links.get(i).click();
                    break;
            }
        }
    }
    //Method for scrolling to the link
    public void scrollToTheLink(String linkName){
        for (int i=0; i<links.size(); i++){
            if (links.get(i).getText().equals(linkName)){
                scrollToElement(links.get(i));
                break;
            }
        }
    }
}
