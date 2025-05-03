package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AdminPage;
import page.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AdminTestRunner extends Setup {

   @Test(priority = 1,description = "Admin login")
    public void login(){
        LoginPage loginPage = new LoginPage(driver);

        if(System.getProperty("email") !=null && System.getProperty("password") !=null){
            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        }else {
            loginPage.doLogin("admin@test.com", "admin123" );
        }
    }

    @Test(priority = 2,description = "Search updated gmail ")
    public void searchUpdatedGmail() throws IOException, ParseException {


        AdminPage adminPage = new AdminPage(driver);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email =(String) userObj.get("email");
        adminPage.searchInput.sendKeys(email);
        List<WebElement> allData = driver.findElements(By.xpath("//tbody/tr[1]/td"));
        String actualEmail = allData.get(2).getText();
        Assert.assertTrue(actualEmail.contains(email));


    }

}
