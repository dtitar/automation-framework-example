package com.github.dtitar.pages.elements.eventdescription;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

@Getter
public class EventPart {

    private final SelenideElement eventPartRootElement;

    public EventPart(SelenideElement eventPartRootElement) {
        this.eventPartRootElement = eventPartRootElement;
    }

    public Market getMarket(String name) {
        return new Market(eventPartRootElement.$$x("./following-sibling::div[contains(@class,'part__markets')]/div")
                .findBy(text(name))
                .shouldBe(visible));
    }
}
