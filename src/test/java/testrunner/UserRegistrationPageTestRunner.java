package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.UserRegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.text.ParseException;

public class UserRegistrationPageTestRunner extends Setup {

    @Test (priority = 1, description = "User registration")
    public void userRegistration() throws IOException, ParseException, org.json.simple.parser.ParseException, InterruptedException {
        driver.findElement(By.linkText("Register")).click();
        UserRegistrationPage user = new UserRegistrationPage(driver);

        Faker faker = new Faker();
        String firstName = faker.name().fullName();
        String lastName = faker.name().lastName();
        String email = "nadim.cse.edu+"+ Utils.generateRandomNumber(1000, 9999)+"@gmail.com";
        String password = "1234";
        String phone = "0160"+ Utils.generateRandomNumber(1000000, 9999999);
        String address = faker.address().fullAddress();

        UserModel userModel = new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phone);
        userModel.setAddress(address);
        user.userRegistration(userModel); // user registration

        // save user info in json file
        JSONObject userObj = new JSONObject();
        userObj.put("firstName", firstName);
        userObj.put("lastName", lastName);
        userObj.put("email", email);
        userObj.put("password", password);
        userObj.put("phone", phone);
        userObj.put("address", address);
        Utils.saveUserData(userObj, "./src/test/resources/users.json");
        Thread.sleep(3000);
        assertEmail();


    }


    public void assertEmail() throws InterruptedException, IOException {
        Thread.sleep(2000);
        String confirmationEmailActual = Utils.readLatestMail();
        String confirmationEmailExpected = "Welcome to our platform!";
        System.out.println(confirmationEmailActual);
        Thread.sleep(2000);
        Assert.assertTrue( confirmationEmailActual.contains(confirmationEmailExpected) );
    }


}
