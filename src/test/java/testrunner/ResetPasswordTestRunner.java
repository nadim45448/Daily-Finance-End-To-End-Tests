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
import page.ResetPasswordPage;
import utils.Utils;
import java.io.FileReader;
import java.io.IOException;

public class ResetPasswordTestRunner extends Setup {

    private ResetPasswordPage reset;

    @BeforeClass
    public void initUserController(){
        reset = new ResetPasswordPage(driver);

    }
    public void clearData() throws InterruptedException {
        Thread.sleep(2000);
        reset.inputEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);

    }

    @Test(priority = 1, description = "Attempt to reset password by not registered email")
    public void wrongResetPassword() throws InterruptedException {
        driver.findElement(By.linkText("Reset it here")).click();

        reset.resetPassword("abc1@gmail.com");
       String actualMessage = driver.findElement(By.tagName("p")).getText();
        System.out.println(actualMessage);
        String expectedMessage = "Your email is not registered";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        clearData();

    }


    @Test(priority = 2, description = "Keep input email field empty and check error message")
    public void sendEmptyMail()  {

        reset.resetPassword("");
        String requiredActual = reset.inputEmail.getAttribute("validationMessage");
        String expectedTxt = "Please fill out this field";
        System.out.println(requiredActual);
        Assert.assertTrue(requiredActual.contains(expectedTxt));
    }

    @Test(priority = 3, description = "Reset password by valid registered gmail and retrieve password reset mail from gmail and set new password")
    public void restPasswordByValidEmail() throws IOException, ParseException, InterruptedException, java.text.ParseException {

        JSONParser  parser= new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject obj = (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email = (String) obj.get("email");

        reset.resetPassword(email);

        Thread.sleep(3000);
        String resetPasswordUrl = retrievePassword();
        driver.get(resetPasswordUrl);
        String password = "abc1234";
        obj.put("password", password);
        Utils.saveUserData(obj, "./src/test/resources/users.json");
        reset.inputPassword.get(0).sendKeys(password);
        reset.inputPassword.get(1).sendKeys(password);
        reset.btnSubmit.click();



    }
    public String retrievePassword() throws IOException {
        String latestMail = Utils.readLatestMail();  // Read the latest email content

        String urlRegex = ".*?(https?://\\S+).*";
        String extractedUrl = latestMail.replaceFirst(urlRegex, "$1");

        if (!extractedUrl.equals(latestMail)) {
            System.out.println("Extracted URL: " + extractedUrl);
            return extractedUrl;
        } else {
            System.out.println("No URL found in the text.");
            return null;
        }

    }





}
