package org.ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {
    private SelenideElement projectTitle = $(By.xpath("//span[@class='title']"));
    private SelenideElement projectOptionsIcon = $(By.xpath("//section[@id='main']//div[@class = 'dropdown']/a"));
    private SelenideElement createOption =$(By.xpath("//div[@id='dropdown']/ul/li[1]/a"));
    private SelenideElement taskName = $(By.id("form-title"));
    private SelenideElement saveButton  = $(By.xpath("//button"));
    private SelenideElement createdTask = $(By.xpath("//div[contains(@class,'table-list-row')]"));
    private SelenideElement dashboard = $(By.xpath("//h1/span/a"));
    private SelenideElement myTasks = $(By.xpath("//div[@class='sidebar']//li/a[contains(@href, '/tasks')]"));
    private SelenideElement task = $(By.xpath("//div[@class='table-list']/div[@class='table-list-row color-yellow']//span/a"));





    public SelenideElement getProjectTitle() {
        return projectTitle;
    }

    public void clickCreateTask() {
        projectOptionsIcon.click();
        createOption.click();
        taskName.setValue("Test Task");
        saveButton.click();
    }

    public SelenideElement getCreatedTask() {
        return createdTask;
    }
    public void clickDashboard() {
        dashboard.click();
    }

    public void clickSidebar() {
        myTasks.click();
    }

    public void clickTask () {
        task.click();
    }
}
