package api;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import org.ui.Application;

public class MainAPITest {
Application app = new Application();
    private String usrname = "jsonrpc";
    private String pwd = "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd";

    @Test
    public void createUser () {
        app.createUser(usrname, pwd);
        app.getAllUsers(usrname, pwd);


    }
}
