package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import org.ui.Application;

import static org.hamcrest.CoreMatchers.*;

public class MainAPITest {
    Application app = new Application();
    private String usrname = "jsonrpc";
    private String pwd = "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd";

    @Test (testName = "createUser")
    public void createUser() {
        Response response = app.createUser(usrname, pwd);

        ValidatableResponse vp = response.then();
        vp.body("result", not("null"));

    }

    @Test
    public void removeUser () {
        app.createUser(usrname, pwd);
        Response response  =  app.removeUser(usrname, pwd);
        ValidatableResponse vp = response.then();
        vp.body("result", is(true));
    }

    @Test
    public void createProject() {
        Response response = app.createProject(usrname,pwd);
        ValidatableResponse vp = response.then();
        vp.body("result", not("null"));

    }

    @Test
    public void removeProject() {
        app.createProject(usrname, pwd);
        Response response = app.removeProject(usrname, pwd);
        ValidatableResponse vp = response.then();
        vp.body("result", is(true));
    }

    @Test
    public void createTask (){
        app.createProject(usrname, pwd);
        Response response = app.createTask(usrname, pwd);
        ValidatableResponse vp = response.then();
        vp.body("result", not("null"));

    }

    @Test
    public void removeTask () {
        Response createProject = app.createProject(usrname,pwd);
        String projectId = app.getProjectId(usrname, pwd);
        Response createTask = app.createTask(usrname, pwd);
        String id = app.getTaskId(usrname, pwd, projectId);
        Response r1 = app.removeTask(usrname, pwd, id);
        ValidatableResponse vp = r1.then();
        vp.body("result", is(true));


    }
}
