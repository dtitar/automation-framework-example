package com.github.dtitar.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

@Getter
public class WheelOfFortuneRegistrationPage extends BasePage {

    private ConfirmMobilePhonePopup confirmMobilePhonePopup;
    private SelenideElement wheelOfFortuneIframe = $("iframe[class*=promo-landing]");
    private SelenideElement mobilePhoneField = $("input[type=tel]");
    private SelenideElement registerButton = $("#registration");

    public WheelOfFortuneRegistrationPage() {
        wheelOfFortuneIframe.shouldBe(visible);
        switchTo().frame(wheelOfFortuneIframe);
    }

    public ConfirmMobilePhonePopup clickRegisterButton() {
        registerButton.shouldBe(enabled).click(ClickOptions.usingJavaScript());
        confirmMobilePhonePopup = new ConfirmMobilePhonePopup();
        return confirmMobilePhonePopup;
    }

    @Getter
    public static class ConfirmMobilePhonePopup {
        private SelenideElement popupForm = $("#jsPopup");
        private SelenideElement codeField = $("#codeInput");
        private SelenideElement sendButton = $("#sendButton");

        public ConfirmMobilePhonePopup() {
            popupForm.shouldBe(visible);
        }
    }
}
