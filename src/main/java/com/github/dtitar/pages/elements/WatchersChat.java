package com.github.dtitar.pages.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

@Getter
public class WatchersChat {
    private final SelenideElement watchersChatWindow = $("[class=chat]");
    private final SelenideElement inputMessageField = $("[class=chat__textarea-item]");
    private final SelenideElement watchersChatIframe = $("iframe[class*=chat-watchers__chat]");
    private final SelenideElement sendMessageBtn = $("[class=chat__message-send-btn]");

    public WatchersChat () {
        switchTo().frame(watchersChatIframe);
        watchersChatWindow.shouldBe(visible);
    }
}