package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResetPasswordPage {


    @FindBy(tagName = "input")
    public   WebElement inputEmail;
    @FindBy(tagName = "button")
    public WebElement btnSubmit;

    @FindBy(tagName = "input")
    public List<WebElement> inputPassword;


    public ResetPasswordPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void resetPassword(String email){
        inputEmail.sendKeys(email);
        btnSubmit.click();

    }




}
