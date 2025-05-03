package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import page.UpdateGmailPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UpdateGmailTestRunner extends Setup {



    @BeforeTest
    public void setAuth() throws ParseException, IOException, InterruptedException {
        Utils.setAuth(driver);

    }

    @Test
    public void updateGmail() throws InterruptedException, IOException, ParseException, java.text.ParseException {
        UpdateGmailPage update = new UpdateGmailPage(driver);
        JSONParser parser= new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject obj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String prevEmail = (String) obj.get("email");


        Thread.sleep(1500);
        update.btnProfileIcon.click();
        update.btnProfileMenuItems.get(0).click();
        update.button.get(1).click(); //Click on edit button

        String newEmail ="nadim.cse.edu+"+Utils.generateRandomNumber(1000, 9999)+"@gmail.com";
        System.out.println("previous email:"+prevEmail);
        System.out.println("new email:"+newEmail);

        update.email.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        update.email.sendKeys(newEmail);
        update.button.get(2).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        obj.put("email",newEmail);
        Utils.saveUserData(obj, "./src/test/resources/users.json");
        update.doLogout();


    }



}
