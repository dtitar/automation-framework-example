package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Getter
public class RegistrationPage extends BasePage {

    public RegistrationPage() {
        switchTo().defaultContent();
        Wait().until(visibilityOf(registerButton));
    }

    private SelenideElement birthdayField = $("input[name=birthDate]");
    private SelenideElement passwordField = $("input[name=password]");
    private SelenideElement registerButton = $("button[class*=registration]");

    public void setCitizenship(String countryName) {
        $("input[name=countryName] ~ svg").click();
        $$("[class*=auto-complete-option-]").findBy(text(countryName)).click();
    }

}
