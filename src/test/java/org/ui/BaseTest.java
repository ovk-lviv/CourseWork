package org.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BaseTest {
    private String usrname = "jsonrpc";
    private String pwd = "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd";


    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {

        if (browser.equalsIgnoreCase("firefox")) {
            Configuration.browser = "firefox";

        } else if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
        } else if (browser.equalsIgnoreCase("headless")) {
            Configuration.headless = true;
        }
    }

    @BeforeMethod
    public void createUser() {
        Application app = new Application();
        app.createUser(usrname, pwd);

    }

    @AfterMethod
    public void removeUser() {
        Application app = new Application();
        app.getUserId(usrname, pwd);
        app.removeUser(usrname, pwd);
    }

}