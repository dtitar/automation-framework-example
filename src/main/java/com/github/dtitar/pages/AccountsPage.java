package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.dtitar.pages.elements.accounts.Statement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Getter
public final class AccountsPage extends BasePage {

    private final SelenideElement betAmountField = $("input[class*=payment-controller__input]");
    private final SelenideElement goToPaymentButton = $("[class*=payment-controller__submit]");
    private final SelenideElement refillTab = $x("//a[text()='Пополнение']");
    private final SelenideElement withdrawalTab = $x("//a[text()='Выплата выигрыша']");
    private final SelenideElement statementTab = $x("//a[text()='Выписка']");
    private final SelenideElement simpleIdNotificationInfo = $("[class*='simple-id-notification__info']");
    private final SelenideElement simpleIdNotificationRestrictions = $("[class*=simple-id-notification__restrictions-trigger]");
    private final SelenideElement simpleIdNotificationDetails = $("[class*=simple-id-notification__details]");

    public AccountsPage() {
        $x("//*[text()='Управление интерактивными ставками']").shouldBe(visible, Duration.ofSeconds(30));
    }

    public AccountsPage chooseWallet(String walletName) {
        $$("img[class*=payment-system__picture]").filterBy(attribute("alt", walletName))
                .get(0)
                .click();
        return this;
    }

    public Statement getStatement(int operationNumber) {
        return new Statement(operationNumber);
    }

}
