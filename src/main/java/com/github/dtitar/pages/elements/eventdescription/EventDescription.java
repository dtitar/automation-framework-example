package com.github.dtitar.pages.elements.eventdescription;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class EventDescription {
    private final ElementsCollection eventParts = $$("[id*=event__part-title]");
    private final SelenideElement firstCoefficient = $("[itemprop=offers]:first-of-type");

    private final SelenideElement playVideoBtn = $("[class*=event-header__panel]").find("[class*=event-header__video]");
    private final SelenideElement videoBox = $("[class*=event__widget_video-]");
    private final SelenideElement lmtWidgetBtn = $(byText("ВИДЖЕТ"));
    private final SelenideElement lmtWidgetBox = $("[class*=bui-widget-betradar]");

    private final SelenideElement statisticsBtn = $(byText("СТАТИСТИКА"));

    public EventPart getEventPart(String name) {
        return new EventPart(eventParts.findBy(text(name))
                .shouldBe(visible));
    }
}
