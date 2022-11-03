package com.github.dtitar.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

@Getter
public final class DocumentsProcessingPage extends BasePage {

    private SelenideElement documentsProcessingForm = $("[class*=documents-processing]");
    private SelenideElement status = $("[class*=documents-processing__status]");
    private SelenideElement info = $("[class*=documents-processing__info]");
    private SelenideElement button = $("[class*=documents-processing__button]");

    public DocumentsProcessingPage() {
        documentsProcessingForm.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }


    public void waitForDataProcessingToComplete() {
        info.should(disappear, Duration.ofSeconds(120));
    }
}
