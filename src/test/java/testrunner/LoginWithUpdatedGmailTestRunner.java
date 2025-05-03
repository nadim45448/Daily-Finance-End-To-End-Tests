package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginWithUpdatedGmailTestRunner extends Setup {

  private LoginPage loginPage;
  @BeforeClass
    public void initLoginPage(){
      loginPage = new LoginPage(driver);

    }
      @Test(priority = 1, description = "Attempt to login by previous gmail is failed")
    public void loginWithPreviousGmail() throws IOException, ParseException, InterruptedException {

        JSONParser parser= new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject obj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String password = (String) obj.get("password");
        loginPage.doLogin("nadim.cse.edu+7144@gmail.com", password);

        String errorActualText = loginPage.errorMessage.getText();
        String errorExpectedText = "Invalid email or password";
        Assert.assertTrue(errorActualText.contains(errorExpectedText));
        Thread.sleep(2000);
        clearData();
    }

    @Test(priority = 2, description = "Login with updated gmail")
    public void loginWithUpdateGmail() throws IOException, ParseException {


        JSONParser parser= new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject obj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String gmail = (String) obj.get("email");
        String password = (String) obj.get("password");
        loginPage.doLogin(gmail, password);
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));
        loginPage.doLogout();

    }

    public void clearData(){
        loginPage.txtEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.txtPassword.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);

    }


}
