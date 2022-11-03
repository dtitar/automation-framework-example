package com.github.dtitar.pages.elements.mybets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Represents a widget with bets, that opens when client clicks on the 'Ставки' button in the header
 */
public class MyBetsWidget {

    public boolean isDisplayed() {
        return $("[class*=auth-panel__bets-modal]").isDisplayed();
    }

    public WidgetBet getWidgetBet(int index) {
        return new WidgetBet($$("div[class*=stake-]").get(index));
    }

}
