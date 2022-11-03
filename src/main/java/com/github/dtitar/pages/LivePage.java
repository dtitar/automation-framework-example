package com.github.dtitar.pages;

import com.codeborne.selenide.ElementsCollection;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

@Getter
@Slf4j
public class LivePage extends BasePage {

    private final ElementsCollection videoIconsWithEnabledVideoAndStatistics =
            $$x("//*[contains(@class,'stat bui-event-row-widget__statistics') and not(contains(@class,'disable'))]" +
                    "/preceding-sibling::*[contains(@class,'bui-event-row-widget__video') and not(contains(@class,'disable'))]");

    public LivePage() {
        $(byId("content")).find("[class*=live-]").shouldBe(visible);
    }
}