package org.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class MainPage {
    private SelenideElement createProjectLink = $(By.xpath("//section[@id='main']/div/ul/li//a[1]"));
    private SelenideElement projectName = $(By.xpath("//input[@id='form-name']"));
    private SelenideElement saveButton  = $(By.xpath("//button"));

    public ProjectPage createProject(String name) {
        createProjectLink.click();
        projectName.setValue(name);
        Selenide.sleep(5000);
        saveButton.click();
        return new ProjectPage();
    }
}
