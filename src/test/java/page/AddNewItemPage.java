package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utils;

import java.util.List;

public class AddNewItemPage {
    WebDriver driver;

    @FindBy(className = "add-cost-button")
   public WebElement btnAddCost;

    @FindBy(tagName = "button")
    public List<WebElement>btn;

    @FindBy(tagName = "input")
   public List<WebElement>txtInput;


    @FindBy(tagName = "textarea")
   public WebElement txtArea;
    @FindBy(css = "[type=submit]")
   public WebElement btnSubmit;

    public AddNewItemPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void addNewItem(String itemName,  String amount, String remarks) throws InterruptedException {
        btnAddCost.click();
        txtInput.get(0).sendKeys(itemName);
        btn.get(2).click();
        txtInput.get(2).sendKeys(amount);
        Utils.scroll(driver,500);
       txtArea.sendKeys(remarks!=null ? remarks : "");
       btnSubmit.click();

    }

    public void showItem() throws InterruptedException {

        List<WebElement> firstRowData = driver.findElements(By.xpath("//tbody/tr[1]/td"));
        String firstItem = firstRowData.get(0).getText();
        Thread.sleep(1500);
        Assert.assertTrue(firstItem.contains("Grapes"));
        List<WebElement> secondRowData = driver.findElements(By.xpath("//tbody/tr[2]/td"));
        String secondItem = secondRowData.get(0).getText();
        Thread.sleep(1500);
        Assert.assertTrue(secondItem.contains("Strawberry"));

    }

}
