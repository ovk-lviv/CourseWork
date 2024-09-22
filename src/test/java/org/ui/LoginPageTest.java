package org.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest {

    // test with incorrect creds
    @Test
    public void logInInvalid() {
        open("http://localhost/login");
        LoginPage lgp = new LoginPage();
        lgp.logIn("admin", "12345");
        Assert.assertTrue(lgp.getErrorBlock().isDisplayed());
    }

    @Test
    public void logInCorrect (){
        open("http://localhost/login");
        LoginPage lgp = new LoginPage();

        Assert.assertTrue(lgp.logIn("admin", "admin").getClass().equals(MainPage.class));
    }
}
