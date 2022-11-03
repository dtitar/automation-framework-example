package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class MainPage extends BasePage {
    private final SelenideElement firstEventOnPage = $x("(//*[contains(@class,'bui-event-row__command')])[1]");

    public MainPage() {
        $("[class*=promo-slider]").shouldBe(visible);
        closeCookieWarningMessage();
    }
}