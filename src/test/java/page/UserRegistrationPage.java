package page;

import config.UserModel;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserRegistrationPage {
    @FindBy(tagName = "input")
    List<WebElement> txtInput;

    @FindBy(id = "register")
    WebElement btnRegister;

    public UserRegistrationPage(WebDriver driver){
        PageFactory.initElements(driver, this);

    }

    public void userRegistration(UserModel userModel){
        txtInput.get(0).sendKeys(userModel.getFirstName() != null ? userModel.getFirstName():""); // firstName
        txtInput.get(1).sendKeys(userModel.getLastName() != null ? userModel.getLastName():""); //lastName
        txtInput.get(2).sendKeys(userModel.getEmail() != null ? userModel.getEmail():""); // email
        txtInput.get(3).sendKeys(userModel.getPassword() != null ? userModel.getPassword():""); // password
        txtInput.get(4).sendKeys(userModel.getPhoneNumber() != null ? userModel.getPhoneNumber():""); // phone no
        txtInput.get(5).sendKeys(userModel.getAddress() != null ? userModel.getAddress():""); // address
        txtInput.get(6).click(); // male gender
        txtInput.get(8).click(); // accept terms
        btnRegister.click();
    }

    public void csvRegistration(String firstName, String lastName, String email, String password, String phoneNumber,String address) throws IOException, ParseException, org.json.simple.parser.ParseException {
        txtInput.get(0).sendKeys(firstName);
        txtInput.get(1).sendKeys(lastName);
        txtInput.get(2).sendKeys(email);
        txtInput.get(3).sendKeys(password);
        txtInput.get(4).sendKeys(phoneNumber);
        txtInput.get(5).sendKeys(address);
        txtInput.get(6).click();
        txtInput.get(8).click();
        btnRegister.click();

        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstName);
        userObj.put("lastName", lastName);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("phone", phoneNumber);
        userObj.put("address", address);
        Utils.saveUserData(userObj, "./src/test/resources/users.json");

    }


}
