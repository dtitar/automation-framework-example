package com.github.dtitar.pages.elements.mybets;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;

/**
 * Represents one stake entry in the table on the 'My bets' page
 */
public final class StakeBlock {

    private static final By DATE_HEADER = cssSelector("[class*=my-bets__date-header]");
    private static final By DATE = cssSelector("[class*=stake__date]");
    private static final By TITLE = cssSelector("[class*=stake__content-title]");
    private static final By AMOUNT = cssSelector("[class*=stake__grid_3]");
    private static final By COEFFICIENT = cssSelector("[class*=stake__grid_4]");
    private static final By RESULT = cssSelector("[class*=stake__grid_5]");
    private static final By BARCODE = cssSelector("[id*=stake__link]");
    private static final By STAKE_ITEMS = cssSelector("[class*=stake__item]");
    private static final By OPEN_STAKE_ITEMS_ARROW = cssSelector("[class*=stake__icon-arrow]");
    private static final By STAKE_ITEMS_CONTENT_BLOCK = cssSelector("[class*=dropdown-wrapper]");

    private static final DateTimeFormatter RECORD_DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "HH:mm:ss d MMMM yyyy г.", new Locale("ru"));

    private SelenideElement stakeBlockElement;

    private List<StakeItem> stakeItems;
    private LocalDateTime date;
    private String title;
    private double amount;
    private double coefficient;
    private String result;
    private String barcode;

    //todo: при необходимости проверять записи в истории пари более подробно требуется доработать данный класс
    public StakeBlock(SelenideElement stakeBlockElement) {
        this.stakeBlockElement = stakeBlockElement;
//        this.date = makeFullStakeDate();
//        this.amount = Utils.convertToDouble(stakeBlockElement.find(AMOUNT).getText());
//        this.coefficient = Utils.convertToDouble(stakeBlockElement.find(COEFFICIENT).getText());
        //this.result = ;

        if (isNotOdinar()) {
            openStakeItems();
        }
        this.barcode = stakeBlockElement.find(BARCODE)
                .getText();
//        this.stakeItems = stakeBlockElement.findAll(STAKE_ITEMS)
//                .asFixedIterable()
//                .stream()
//                .map(StakeItem::new)
//                .collect(Collectors.toList());
    }

    public StakeBlock(String barcode) {
        this($(format("#stake__block_%s", barcode)));
    }

    private boolean isNotOdinar() {
        return stakeBlockElement.find(OPEN_STAKE_ITEMS_ARROW)
                .exists();
    }

    private LocalDateTime makeFullStakeDate() {
        return LocalDateTime.parse(format("%s %s", stakeBlockElement.find(DATE)
                .getText()
                .split(",")[0], stakeBlockElement.find(DATE_HEADER)
                .getText()), RECORD_DATE_FORMATTER);
    }

    /**
     * Возвращает баркод операции
     *
     * @return @{@link String} уникальный баркод операции
     */
    public String getBarcode() {
        return stakeBlockElement.find(BARCODE)
                .getText()
                .replace("#", "");
    }

    /**
     * Для каждого события исходы в истории пари скрыты и чтобы их открыть нужно кликнуть на элемент ^
     * Данный метод открывает список исходов, чтобы можно было получить информацию по ним
     */
    public StakeBlock openStakeItems() {
        if (isStakeItemsClosed()) {
            stakeBlockElement.find(OPEN_STAKE_ITEMS_ARROW)
                    .click();
            stakeBlockElement.find(STAKE_ITEMS_CONTENT_BLOCK)
                    .shouldBe(visible);
            sleep(1000);
        }
        return this;
    }

    private boolean isStakeItemsClosed() {
        return !Objects.requireNonNull(stakeBlockElement.find(OPEN_STAKE_ITEMS_ARROW)
                        .getAttribute("class"))
                .contains("icon-arrow_up");
    }
}
