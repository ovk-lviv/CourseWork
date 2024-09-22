package org.ui;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BaseTest {
    private String usrname = "jsonrpc";
    private String pwd = "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd";

    @BeforeTest
    public void createUser() {

        Application app = new Application();
        app.createUser(usrname, pwd);
        System.out.println(app.createUser(usrname, pwd).asString());
    }

    @AfterTest
    public void removeUser() {
        Application app = new Application();
        app.getUserId(usrname, pwd);
        app.removeUser(usrname, pwd);
    }

}