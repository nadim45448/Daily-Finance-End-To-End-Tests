package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import page.LoginPage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends Setup {

    @Test(priority = 1, description = "Login with new password")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=userObj.get("email").toString();
        String password=userObj.get("password").toString();
        loginPage.doLogin(email, password);
       Utils.getToken(driver);
    }
}
