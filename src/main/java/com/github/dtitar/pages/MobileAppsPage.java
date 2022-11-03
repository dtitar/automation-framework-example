package com.github.dtitar.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

@Slf4j
@Getter
public class MobileAppsPage {


    private final SelenideElement loadAndroidAppButton = $x("(//img[@alt='android']/parent::a)[2]");
    private final SelenideElement loadIosAppButton = $x("(//img[@alt='apple']/parent::a)[2]");
    private final SelenideElement autoLoadingMessage = $x("//*[text()='Загрузка начнётся автоматически...']");
    private final SelenideElement pressIfNotLoadButton = $x("//a[@id='download-link']");
    private final SelenideElement mobileAppsIframe = $x("//iframe[contains(@class,'promo-landing')]");

    public MobileAppsPage(){
       mobileAppsIframe.shouldBe(Condition.visible, Duration.ofSeconds(30));
       switchTo().frame(mobileAppsIframe);
       loadAndroidAppButton.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
