package com.github.dtitar.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

@Getter
public final class DocumentsUploadPage extends BasePage {

    private SelenideElement documentsUploadForm = $("[class*=documents-upload__content]");
    private SelenideElement imageBaseInfoField = $("input[name='imageBaseInfo']");
    private SelenideElement imageRegistrationField = $("input[name='imageRegistration']");
    private SelenideElement imageSelfField = $("input[name='imageSelf']");
    private SelenideElement sendDataButton = $("button[type=submit]");

    public DocumentsUploadPage() {
        documentsUploadForm.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
