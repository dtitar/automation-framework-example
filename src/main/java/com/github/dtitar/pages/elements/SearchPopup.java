package com.github.dtitar.pages.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Getter
public class SearchPopup {

    private final SelenideElement searchField = $(".search-input__input-25c7a1");
    private final SelenideElement searchButton = $(".search-input-e25cce > .button-f37d89");
    private final SelenideElement searchResult = $("[class*=search-content__link]");
    private final SelenideElement searchAutoCompleteItem = $("[class*=search-autocomplete__item]");

    public SearchPopup() {
        $("[class*=search-header__description]").shouldBe(visible);
    }

    public void search(String text) {
        searchField.setValue(text);
        searchButton.click();
        searchAutoCompleteItem.should(disappear, Duration.ofSeconds(10));
        searchResult.shouldBe(visible, Duration.ofSeconds(10))
                .click();
    }

    public void search(int value) {
        search(String.valueOf(value));
    }
}
