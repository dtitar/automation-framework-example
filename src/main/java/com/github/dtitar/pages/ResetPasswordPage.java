package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ResetPasswordPage extends BasePage{

    private final SelenideElement resetPasswordHeader = $("[class*=reset-password__heading]");
    private final SelenideElement resetPasswordWithPhoneTab = $x("//*[text()='Номер телефона']/parent::*");
    private final SelenideElement resetPasswordWithEmailTab = $x("//*[text()='Электронная почта']/parent::*");
    private final SelenideElement mobilePhoneField = $("input[name=mobilePhone]");
    private final SelenideElement emailField = $("input[name=email]");
    private final SelenideElement codeBySmsButton = $("button[class*=reset-password]");
    private final SelenideElement sendInstructionByEmailButton = $("button[class*=reset-password]");
    private final SelenideElement resetPasswordWarning = $("[class*=reset-password__warning]");
    private final SelenideElement mobilePhoneInputErrorMessage = $("[class*=better-inputs__error]");
}
