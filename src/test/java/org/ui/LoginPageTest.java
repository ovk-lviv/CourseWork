package org.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest extends BaseTest {
    Application app = new Application();

    String usr = app.getCreatedUserUsername();
    String pwd = app.getCreatedUserPassword();

    // test with incorrect creds
    @Test
    public void logInInvalid() {
        open("http://localhost/login");
        LoginPage lgp = new LoginPage();
        lgp.logIn(usr, "12345");
        Assert.assertTrue(lgp.getErrorBlock().isDisplayed());
    }

    @Test
    public void logInCorrect (){
        open("http://localhost/login");
        LoginPage lgp = new LoginPage();

        Assert.assertTrue(lgp.logIn(usr, pwd).getClass().equals(MainPage.class));
    }
}
