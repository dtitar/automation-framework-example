package com.github.dtitar.pages.elements.mybets;

import com.codeborne.selenide.SelenideElement;

import static org.openqa.selenium.By.cssSelector;

/**
 * Represents a single bet in the betting widget
 */
public class WidgetBet {

    private SelenideElement widgetBetElement;

    public WidgetBet(SelenideElement widgetBetElement) {
        this.widgetBetElement = widgetBetElement;
    }

    /**
     * Returns bet type (e.g Odinar, Express or System)
     * @return bet type
     */
    public String getBetType() {
        return widgetBetElement.find(cssSelector("[class*=stake__bet-type]")).getText();
    }
}
