package org.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TaskPage {
    private SelenideElement comments = $(By.xpath("//div[@id='comments']/preceding-sibling::*"));
    private SelenideElement commentsTextArea = $(By.xpath("//textarea"));
    private SelenideElement saveCommentsButton = $(By.xpath("//button"));
    private SelenideElement commentTitle = $(By.xpath("//div[@class='comment ']"));
    private SelenideElement taskComment = $(By.xpath("//div[@class='comment-content']//p"));
    private SelenideElement closeTaskButton = $(By.id("//a[contains(@href, 'close')]"));
    private SelenideElement closeTaskLink = $(By.xpath("//a[contains(@href, '/close')]"));
    private SelenideElement taskSummary = $(By.xpath("//*[@id=\"task-summary\"]/div/div/div[1]/ul/li[1]/span"));



    public void addComment() {

        comments.click();
        commentsTextArea.setValue("This is a comment");
        saveCommentsButton.click();

    }

    public SelenideElement getCommentTitle() {
        return commentTitle;
    }

    public void closeTask() {
        closeTaskLink.scrollIntoView(false);
        Selenide.sleep(5000);

        closeTaskButton.click();
    }

    public SelenideElement getTaskSummary() {
        return taskSummary;
    }

    public SelenideElement getTaskComment() {
        return taskComment;
    }
}

