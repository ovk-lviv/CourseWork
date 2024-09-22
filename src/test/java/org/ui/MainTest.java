package org.ui;

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
        Selenide.sleep(3000);
        ProjectPage pp = mp.createProject(project);
        Selenide.sleep(3000);
        Assert.assertTrue(pp.getProjectTitle().text().equals(project));


    }

    @Test
    public void createTask() {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);
        Selenide.sleep(5000);
        ProjectPage pp = mp.createProject(project);
        pp.clickCreateTask();
        pp.clickDashboard();
        Selenide.sleep(5000);
        pp.clickSidebar();

        Assert.assertTrue(pp.getCreatedTask().exists());

    }

    @Test
    public void createComment () {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);
        Selenide.sleep(5000);
        ProjectPage pp = mp.createProject(project);
        pp.clickCreateTask();
        pp.clickDashboard();
        pp.clickTask();
        TaskPage tp = new TaskPage();
        Selenide.sleep(5000);
        tp.addComment();
        Selenide.sleep(5000);

        Assert.assertEquals(tp.getTaskComment().text(), "This is a comment");
    }

    @Test
    private void closeTask() {
        LoginPage lp = new LoginPage();
        MainPage mp = lp.logIn(usr, pwd);
        Selenide.sleep(5000);
        ProjectPage pp = mp.createProject(project);
        pp.clickCreateTask();
        pp.clickDashboard();
        pp.getCreatedTask();
        TaskPage tp = new TaskPage();
        tp.closeTask();

        Assert.assertEquals(tp.getTaskSummary().text().trim(), "closed");


    }
}
