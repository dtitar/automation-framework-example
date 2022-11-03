package com.github.dtitar.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Getter
public class ShopsOnlinePage extends BasePage {

    private SelenideElement simpleIdentificationForm = $("#simple-identification");
    private SelenideElement lastNameField = $("input[name='lastName']");
    private SelenideElement firstNameField = $("input[name='firstName']");
    private SelenideElement middleNameField = $("input[name='middleName']");
    private SelenideElement noMiddleNameCheckbox = $("input[name='noMiddleName']");
    private SelenideElement genderField = $("div[class*=select-]");
    private ElementsCollection genderOptions = $$("label[class*=select__option]");
    private SelenideElement documentNumberField = $("input[name='documentNumber']");
    private SelenideElement birthDateField = $("input[name='birthDate']");
    private SelenideElement innField = $("input[name='inn']");
    private SelenideElement snilsField = $("input[name='snils']");
    private SelenideElement continueButton = $("button[class*=simple-identification__submit]");
    private SelenideElement backToSelectingIdentificationMethodButton = $("a[class*=identification-online__button_back]");


    public ShopsOnlinePage() {
        Wait().until(visibilityOf(simpleIdentificationForm));
    }
}
