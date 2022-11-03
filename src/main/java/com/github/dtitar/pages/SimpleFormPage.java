package com.github.dtitar.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class SimpleFormPage extends BasePage{

    public SimpleFormPage() {
        $("[class*=confirm-waiting__content]").shouldBe(visible);
    }

    public SimpleFormPage waitForDataToBeSent() {
        Wait().withMessage("Ожидание отправки документов в Цупис")
                .withTimeout(ofSeconds(60))
                .until(invisibilityOf(LOADER));
        return this;
    }
}
