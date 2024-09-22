package org.ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage
{
    private SelenideElement username = $(By.id("form-username"));
    private SelenideElement password = $(By.id("form-password"));
    private SelenideElement logInButton = $(By.xpath("//div/button"));
    private SelenideElement errorBlock = $(By.xpath("//p[text()= 'Bad username or password']"));

    public MainPage logIn(String usrname, String pwd){
        open("http://127.0.0.1/login");
        username.setValue(usrname);
        password.setValue(pwd);
        logInButton.click();
        return new MainPage();
    }

    public SelenideElement getErrorBlock() {
        return errorBlock;
    }
}
