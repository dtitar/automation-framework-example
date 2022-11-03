package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.dtitar.pages.elements.eventdescription.EventDescription;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

@Getter
public final class EventDescriptionPage extends BasePage {

    private final SelenideElement watchersChatBtn = $("[class*=chat-watchers-button]");

    private EventDescription eventDescription;

    public EventDescriptionPage() {
        $(byId("content")).find("[class*=event-]").shouldBe(visible, Duration.ofSeconds(10));
        this.eventDescription = page(EventDescription.class);
    }
}
