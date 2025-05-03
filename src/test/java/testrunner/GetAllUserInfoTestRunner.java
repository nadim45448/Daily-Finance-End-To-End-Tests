package testrunner;

import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import page.AdminPage;
import page.LoginPage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GetAllUserInfoTestRunner extends Setup {

    @Test(priority = 1,description = "Admin login")
    public void login(){
        LoginPage loginPage = new LoginPage(driver);

        if(System.getProperty("username") !=null && System.getProperty("password") !=null){
            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        }else {
            loginPage.doLogin("admin@test.com", "admin123" );
        }
    }

    @Test(priority = 2,description = "Get all user info")
    public void scarpData() throws InterruptedException, IOException {



        try {
            List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/test/resources/allUserInfo.txt"));
            writer.write("First Name | Last Name | Email | Phone Number | Address | Gender | Registration Date");
            writer.newLine();

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));


                StringBuilder rowText = new StringBuilder();
                for (int i = 0; i < cells.size() - 1; i++) {
                    rowText.append(cells.get(i).getText().trim()).append(" | ");
                }

                // Remove trailing separator and write
                writer.write(rowText.substring(0, rowText.length() - 3));
                writer.newLine();
            }
            writer.close();
            System.out.println(" User data exported to users.txt");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }




}
