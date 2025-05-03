package utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.ParseException;
import java.time.Duration;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utils {
    public static Properties props;
    public static FileInputStream fs;

    public static int generateRandomNumber(int min, int max){
        double randomNumber= Math.random()*(max-min)+min;
        return (int)randomNumber;
    }

    public static  void saveUserData(JSONObject obj, String filePath) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        jsonArray.add(obj);
        FileWriter writer = new FileWriter(filePath);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();


    }

    public static String getEmailList() throws IOException {
        props = new Properties();
        fs = new FileInputStream("./src/test/resources/config.properties");
        props.load(fs);
        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response res = given().contentType("application/json")
                .header("Authorization","Bearer "+props.getProperty("google_access_token"))
                .when().get("/gmail/v1/users/me/messages");

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.get("messages[0].id");
}

public static String readLatestMail() throws IOException {
    String messageId= getEmailList();
    RestAssured.baseURI = "https://gmail.googleapis.com";
    Response res=given().contentType("application/json")
            .header("Authorization","Bearer "+props.getProperty("google_access_token"))
            .when().get("/gmail/v1/users/me/messages/"+ messageId);

    JsonPath jsonPath = res.jsonPath();
    String message = jsonPath.get("snippet");
    return message;
}

    public static void getToken(WebDriver driver) throws IOException {
        //wait until the authToken is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')") != null);

        //get the authToken from the localstorage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken');");
        String authTokenData=(String) js.executeScript("return window.localStorage.getItem('authTokenData');");
        System.out.println("Auth Token Retrieved: " + authToken);
        System.out.println("Auth Token Retrieved: " + authTokenData);

        // Save the auth token to a localstorage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken", authToken);
        jsonObject.put("authTokenData", authTokenData);
        FileWriter writer=new FileWriter("./src/test/resources/localstorage.json");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }

    public static void setAuth(WebDriver driver) throws org.json.simple.parser.ParseException, InterruptedException, IOException {
        JSONParser jsonParser=new JSONParser();
        JSONObject authObj= (JSONObject) jsonParser.parse(new FileReader( "./src/test/resources/localstorage.json"));
        String authToken= authObj.get("authToken").toString();
        String authTokenData= authObj.get("authTokenData").toString();
        System.out.println(authToken);
        //inject object in localstorage at browser
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", authToken);
        js.executeScript("window.localStorage.setItem('authTokenData', arguments[0]);", authTokenData);
        Thread.sleep(2000);
    }

    public static void scroll(WebDriver driver, int height) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
    }
}
