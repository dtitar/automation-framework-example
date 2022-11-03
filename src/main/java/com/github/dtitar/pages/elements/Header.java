package com.github.dtitar.pages.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;

@Getter
public class Header {

    private SearchPopup searchPopup;

    private final SelenideElement loginButton = $("#auth-panel>button");
    private SelenideElement registerButton = $("#header-sign-up");
    private final SelenideElement searchButton = $("#header-search");
    private final SelenideElement menuItemLive = $x("//a[@href='/bets/live']");
    private final SelenideElement loadMobileAppButton = $("#header__app");
    private final SelenideElement myBetsButton = $("#header__bets");
    private final SelenideElement personalMenuButton = $("#header__personal-area_menu");
    private final ElementsCollection personalMenuItems = $$("a[id*=header__personal-area]");
    private final ElementsCollection walletItems = $$("[class*=auth-panel__dropdown-item_wallet]");

    private final SelenideElement wallet = $("[class*=auth-panel__wallets-amount]");

    public SearchPopup openSearchPopup() {
        searchButton.click();
        return page(SearchPopup.class);
    }

    public void openPersonalMenuItem(String itemName) {
        personalMenuButton.click();
        personalMenuItems.get(0)
                .shouldBe(visible);
        personalMenuItems.filterBy(text(itemName))
                .first()
                .click();
    }

    public BigDecimal getWalletAmount() {
        return BigDecimal.valueOf(parseDouble(wallet.getText()
                .replaceAll("\\\u20BD", "")
                .replaceAll(" ", "")
                .replaceAll(",", ".")
                .trim())).setScale(2, RoundingMode.UNNECESSARY);
    }
}
