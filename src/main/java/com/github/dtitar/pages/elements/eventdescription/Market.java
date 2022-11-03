package com.github.dtitar.pages.elements.eventdescription;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;

@Getter
public class Market {

    private final SelenideElement marketRootElement;

    public Market(SelenideElement marketRootElement) {
        this.marketRootElement = marketRootElement;
    }

    public Outcome getOutcome(String title) {
        return new Outcome(marketRootElement.findAll("div[class*=bui-outcome]").findBy(have(text(title))).shouldBe(visible));
    }

    public Outcome getOutcome(int index) {
        return new Outcome(marketRootElement.$$("div[class*=bui-outcome]").get(index).shouldBe(visible));
    }
}
