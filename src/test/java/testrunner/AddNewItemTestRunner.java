package testrunner;

import config.AddItemDataset;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.AddNewItemPage;
import page.LoginPage;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;

public class AddNewItemTestRunner extends Setup {
    private AddNewItemPage addItem;

    @BeforeClass
    public void initUserController(){
        addItem = new AddNewItemPage(driver);

    }

    @BeforeTest
    public void setAuth() throws ParseException, IOException, InterruptedException {
        Utils.setAuth(driver);

    }

    @Test(priority = 1, dataProvider = "AddItemCSVData", dataProviderClass = AddItemDataset.class,description = "Add two random items")
    public void addTwoRandomItems(String itemName, String quantity, String amount, String remarks) throws InterruptedException {
        addItem.addNewItem(itemName, amount, remarks);
        Thread.sleep(2000);
       driver.switchTo().alert().accept();

    }

    @Test(priority = 2, description = "Assert 2 items are showing on the item list")
    public void showItem() throws InterruptedException {

        addItem.showItem();

    }



}
