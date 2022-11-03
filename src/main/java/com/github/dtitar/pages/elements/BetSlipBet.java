package com.github.dtitar.pages.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class BetSlipBet {

    private final By amount = By.cssSelector("input[class*=bet__field][id*=betslip__bet]");

    private final SelenideElement rootBetSlipBetElement;

    public BetSlipBet(SelenideElement rootBetSlipBetElement) {
        this.rootBetSlipBetElement = rootBetSlipBetElement;
    }

    public SelenideElement getElement() {
        return rootBetSlipBetElement;
    }

    public SelenideElement getAmount() {
        return rootBetSlipBetElement.find(amount);
    }
}
