package com.github.dtitar.pages.elements.eventdescription;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.enabled;

@Getter
public class Outcome {

    private final SelenideElement outcomeRootElement;

    public Outcome(SelenideElement outcomeRootElement) {
        this.outcomeRootElement = outcomeRootElement;
    }

    public void selectOutcome() {
        outcomeRootElement.shouldBe(enabled)
                .click();
    }

    public boolean isSelected() {
        return outcomeRootElement.isSelected();
    }
}
