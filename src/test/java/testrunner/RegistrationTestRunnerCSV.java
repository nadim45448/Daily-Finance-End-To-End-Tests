package testrunner;

import config.RegistrationDataset;
import config.Setup;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import page.UserRegistrationPage;

import java.io.IOException;
import java.text.ParseException;

public class RegistrationTestRunnerCSV extends Setup {

    @Test(dataProvider = "Registration Dataset", dataProviderClass = RegistrationDataset.class)
    public void registration(String firstName, String lastname, String email, String password,String phoneNumber, String address) throws IOException, ParseException, org.json.simple.parser.ParseException {
        driver.findElement(By.linkText("Register")).click();
        UserRegistrationPage reg = new UserRegistrationPage(driver);

        reg.csvRegistration(firstName, lastname, email, password, phoneNumber, address);




    }
}
