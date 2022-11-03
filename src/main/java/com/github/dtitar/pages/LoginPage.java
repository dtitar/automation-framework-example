package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class LoginPage extends BasePage{

    private final SelenideElement loginForm = $("#auth");
    private final SelenideElement loginWithMobilePhoneTab = $x("//*[text()='Номер телефона']/parent::*");
    private final SelenideElement loginWithEmailTab = $x("//*[text()='E-mail']");
    private final SelenideElement mobilePhoneField = $("input[name=mobilePhone]");
    private final SelenideElement emailField = $("input[name=email]");
    private final SelenideElement passwordField = $("input[name=password]");
    private final SelenideElement submitButton = $("[class*=auth-form__submit]");
    private final SelenideElement authFormErrorNotification = $("[class*=auth-form__error]");
    private final SelenideElement resetPasswordLink = $("a[href='/ResetPassword']");


    public void loginWithMobilePhone(String mobilePhone, String password) {
        loginWithMobilePhoneTab.click();
        mobilePhoneField.shouldBe(visible)
                .setValue(mobilePhone.substring(1));
        passwordField.shouldBe(visible)
                .setValue(password);
        submitButton.shouldBe(visible)
                .click();
    }

    public void loginWithEmail(String email, String password) {
        loginWithEmailTab.click();
        emailField.shouldBe(visible)
                .setValue(email);
        passwordField.shouldBe(visible)
                .setValue(password);
        submitButton.shouldBe(visible)
                .click();
    }
}