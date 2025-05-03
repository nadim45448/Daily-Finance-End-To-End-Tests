package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UpdateGmailPage {

    @FindBy(xpath = "//button[@aria-label='account of current user']")
    public WebElement btnProfileIcon;
    @FindBy(css = "[role=menuitem]")
    public List<WebElement> btnProfileMenuItems;

    @FindBy(tagName = "button")
    public List<WebElement>button;

    @FindBy(css = "[type=email]")
    public WebElement email;

    @FindBy(css = "[type=button]")
    List<WebElement> btnProfile;
    @FindBy(css = "[role=menuitem]")
    List<WebElement> btnLogout;


    public UpdateGmailPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void doLogout(){
        btnProfile.get(0).click();
        btnLogout.get(1).click();
    }
}
