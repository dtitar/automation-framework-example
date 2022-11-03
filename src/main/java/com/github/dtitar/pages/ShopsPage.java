package com.github.dtitar.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Getter
public class ShopsPage extends BasePage {

private SelenideElement identificationContainer = $("[class*=identification__content-container]");
private SelenideElement identifyOnlineOnSiteButton = $x("//*[text()='Онлайн на сайте']/parent::button");
private SelenideElement attachScanOrPhotoOfYourPassportContinueButton = $x("(//button[contains(@class, 'identification__button')])[1]");
private SelenideElement fillPassportDataForYourselfContinueButton = $x("//button[contains(@class, 'identification__button')][2]");
private SelenideElement identifyOnGosuslugiButton = $x("//*[text()='На портале «Госуслуги»']/parent::button");
private SelenideElement allIdentificationMethodsLink = $x("//*[text()='Все способы идентификации']");
private ElementsCollection identificationMethods = $$("[class*=identification__method-container]");

    public ShopsPage() {
        Wait().until(visibilityOf(identificationContainer));
        closeCookieWarningMessage();
    }
}
