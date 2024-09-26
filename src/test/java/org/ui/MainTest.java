package org.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends  BaseTest {
    Application app = new Application();

    String usr = app.getCreatedUserUsername();
    String pwd = app.getCreatedUserPassword();
    String project = app.getProject();

    @Test
    public void createProject () {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);

        ProjectPage pp = mp.createProject(project);

        Assert.assertTrue(pp.getProjectTitle().text().equals(project));


    }

    @Test
    public void createTask() {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);
        ProjectPage pp = mp.createProject(project);
        pp.clickCreateTask();
        pp.clickDashboard();
        pp.clickSidebar();

        Assert.assertTrue(pp.getCreatedTask().exists());

    }

    @Test
    public void createComment () {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);
        ProjectPage pp = mp.createProject(project);
        pp.clickCreateTask();
        pp.clickDashboard();
        pp.clickTask();
        TaskPage tp = new TaskPage();
        tp.addComment();


        Assert.assertEquals(tp.getTaskComment().text(), "This is a comment");
    }

    @Test
    public void closeTask() {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);

        ProjectPage pp = mp.createProject(project);
        pp.clickCreateTask();
        pp.clickDashboard();
        pp.clickTask();
        TaskPage tp = new TaskPage();
        tp.closeTask();

        Assert.assertEquals(tp.getTaskSummaryValue().text().trim(), "closed");


    }
}
