package com.github.dtitar.pages.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

@Getter
public class BetSlip {

    private final SelenideElement makeBetButton = $("#betslip__accept-and-submit");
    private final SelenideElement serviceMessage = $("[class*=betslip__service-message-]");
    private final SelenideElement barcodeLink = $("#betslip__message_barcode-link");
    private final SelenideElement ordinarTab = $("#betslip__types-tabs_ordinar");
    private final SelenideElement expressTab = $("#betslip__types-tabs_express");
    private final SelenideElement comboTab = $("#betslip__types-tabs_combo");
    private final SelenideElement betAmount = $("input[class*=betslip__input]");

    public BetSlipBet getBetSlipBet(int index) {
        return new BetSlipBet($$("div[class*=betslip__bet-]").get(index).shouldBe(visible));
    }

    public int getNumberOfBetSlipBets() {
        return $$("div[class*=betslip__bet-]").size();
    }

    public void waitBetSlipLoaderToDisAppear() {
        Wait().until(invisibilityOfElementLocated(By.cssSelector("[class*=lui-loader-circle]")));
    }

    public String getBarcode() {
        String hrefAttribute = barcodeLink.getAttribute("href");
        return Objects.requireNonNull(hrefAttribute)
                .split("/")[4];
    }
}
