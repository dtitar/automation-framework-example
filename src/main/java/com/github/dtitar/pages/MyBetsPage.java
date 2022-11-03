package com.github.dtitar.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.dtitar.core.exceptions.AutoTestError;
import com.github.dtitar.pages.elements.mybets.BetType;
import com.github.dtitar.pages.elements.mybets.SortOrder;
import com.github.dtitar.pages.elements.mybets.StakeBlock;
import com.github.dtitar.pages.elements.mybets.TimePeriod;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.github.dtitar.core.helpers.Utils.convertToDouble;
import static com.github.dtitar.pages.elements.mybets.SortOrder.ASCENDING;
import static com.github.dtitar.pages.elements.mybets.SortOrder.DESCENDING;
import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Class represents page 'My bets'
 */
@Slf4j
@Getter
public class MyBetsPage extends BasePage {

    public static final String DATE_PATTERN = "HH:mm:ss d MMMM yyyy г.";

    private final SelenideElement currentTimePeriodFilter = $("#my-bets__menu_date-filter");
    private final ElementsCollection timePeriodFilterItems = $$("[id*=my-bets__menu-date-filter-item]");
    private final SelenideElement currentBetTypeFilter = $("#my-bets__menu_bet-type-filter");
    private final ElementsCollection betTypeFilterItems = $$("[id*=my-bets__menu-bet-type-filter-item]");
    private final SelenideElement myBetsButton = $("#header__bets");
    private final ElementsCollection betsDates = $$("[class*=stake__date-]");
    private final ElementsCollection betsType = $$("[class*=stake__result-]");
    private final ElementsCollection paginationButtons = $$("button[class*=events-lazy-bar__button]");

    public MyBetsPage() {
        $(".my-bets__content").shouldBe(visible);
    }

    @Step("Выбрать временной дипазон {0}")
    public void chooseTimePeriod(TimePeriod period) {
        currentTimePeriodFilter.click();
        timePeriodFilterItems.filter(text(period.getTitle()))
                .first()
                .click();
        timePeriodFilterItems.get(0)
                .shouldBe(hidden);
        sleep(2000);
    }

    @Step("Выбрать тип ставок {0}")
    public void chooseBetsType(BetType betType) {
        currentBetTypeFilter.click();
        betTypeFilterItems.filter(text(betType.getTitle()))
                .first()
                .click();
        betTypeFilterItems.get(0)
                .shouldBe(hidden);
    }

    @Step("Проверить, что ставки отображаются за период {0} дней")
    public void verifyBetsAreDisplayedForLastXDays(int days) {
        LocalDateTime boundaryDateTime = LocalDate.now()
                .minusDays(days)
                .atStartOfDay();

        betsDates.asFixedIterable()
                .stream()
                .map(e -> LocalDateTime.parse(getBetFullDate(e), DateTimeFormatter.ofPattern(
                        DATE_PATTERN, new Locale("ru"))))
                .forEach(e -> assertThat(e).as("Проверка даты создания пари")
                        .withFailMessage("Дата создания пари раньше начала ожидаемого периода")
                        .isAfter(boundaryDateTime));
    }

    @Step("Проверить, что ставки отсортированы в {0} порядке")
    public void verifyBetsAreSortedInOrder(SortOrder sortOrder) {
        if (betsDates.size() < 2) {
            log.info("В истории пари меньше 2 записей");
            return;
        }
        List<LocalDateTime> betsDateTime = betsDates.asFixedIterable()
                .stream()
                .map(e -> LocalDateTime.parse(getBetFullDate(e), DateTimeFormatter.ofPattern(
                        DATE_PATTERN, new Locale("ru"))))
                .collect(Collectors.toList());
        for (int i = 0; i < betsDateTime.size() - 2; i++) {

            switch (sortOrder) {
                case ASCENDING:
                    assertThat(betsDateTime.get(i)).as("Проверка сортировки записей в истории пари " + sortOrder.getTitle())
                            .withFailMessage("Порядок сортировки записей в таблице отличается от ожидаемой " + sortOrder.getTitle())
                            .isBefore(betsDateTime.get(i + 1));
                    break;
                case DESCENDING:
                    assertThat(betsDateTime.get(i)).as("Проверка сортировки записей в истории пари " + sortOrder.getTitle())
                            .withFailMessage(format("Порядок сортировки записей в таблице отличается от ожидаемой '%s'\n" +
                                            "дата в строке %d: %s, дата в строке %d: %s",
                                    sortOrder.getTitle(),
                                    i + 1,
                                    betsDateTime.get(i)
                                            .toString(),
                                    i + 2,
                                    betsDateTime.get(i + 1)
                                            .toString()))
                            .isAfter(betsDateTime.get(i + 1));
                    break;
                default:
                    throw new AutoTestError(format("Некорректное значение порядка сортировки: %s, возможные значения %s, %s",
                            sortOrder.getTitle(), ASCENDING.getTitle(), DESCENDING.getTitle()));
            }
        }
    }

    @Step("Проверить, что отображаются ставки типа {0}")
    public void verifyBetsTypeIsDisplayed(BetType betType) {
        switch (betType) {
            case ALL:
                betsType.asFixedIterable()
                        .stream()
                        .forEach(e -> assertThat(isCalculated(Objects.requireNonNull(e.getText())) || isWaitingForCalculation(e))
                                .as("Проверка значения столбца Результат (₽)**")
                                .withFailMessage(format("Значение %s не соответствует заданному фильтру %s, ожидаемое значение - сумма >= 0 или отображение иконки 'часы'",
                                        e.getText(), betType.getTitle()))
                                .isTrue());
                break;
            case WON:
                betsType.asFixedIterable()
                        .stream()
                        .forEach(e -> assertThat(isWon(Objects.requireNonNull(e.getText())))
                                .as("Проверка значения столбца Результат (₽)**")
                                .withFailMessage(format("Значение %s не соответствует заданному фильтру %s, ожидаемое значение - сумма > 0",
                                        e.getText(), betType.getTitle()))
                                .isTrue());
                break;
            case LOST:
                betsType.asFixedIterable()
                        .stream()
                        .forEach(e -> assertThat(isLost(Objects.requireNonNull(e.getText())))
                                .as("Проверка значения столбца Результат (₽)**")
                                .withFailMessage(format("Значение %s не соответствует заданному фильтру %s, ожидаемое значение - 0",
                                        e.getText(), betType.getTitle()))
                                .isTrue());
                break;
            case CALCULATED:
                betsType.asFixedIterable()
                        .stream()
                        .forEach(e -> assertThat(isCalculated(Objects.requireNonNull(e.getText())))
                                .as("Проверка значения столбца Результат (₽)**")
                                .withFailMessage(format("Значение %s не соответствует заданному фильтру %s, ожидаемое значение - сумма >= 0",
                                        e.getText(), betType.getTitle()))
                                .isTrue());
                break;
            case WAITING_FOR_CALCULATION:
                betsType.asFixedIterable()
                        .stream()
                        .forEach(e -> assertThat(isWaitingForCalculation(Objects.requireNonNull(e)))
                                .as("Проверка значения столбца Результат (₽)**")
                                .withFailMessage(format("Значение %s не соответствует заданному фильтру %s, ожидаемое значение - отображается иконка 'часы'",
                                        e.getText(), betType.getTitle()))
                                .isTrue());
                break;
            default:
        }
    }

    private boolean isWon(String value) {
        /*
         Проверка на пустоту необходима, чтобы при проверке значений с типом Ожидающие расчета не происходило
         некорректного падения в методе convertToDouble, а подобное значение считалось не соответствующим фильтру
          */
        if (value.isEmpty()) return false;
        log.info(format("Проверяемое значение, тип пари Выигравшие: %s", value));
        double actualValue = convertToDouble(value.contains("р.") ? value.replaceAll("р.", "")
                .trim() : value);
        return actualValue > 0;
    }

    private boolean isLost(String value) {
        if (value.isEmpty()) return false;
        log.info(String.format("Проверяемое значение, тип пари Проигравшие: %s", value));
        double actualValue = convertToDouble(value.contains("р.") ? value.replaceAll("р.", "")
                .trim() : value);
        return actualValue == 0;
    }

    private boolean isCalculated(String value) {
        if (value.isEmpty()) return false;
        log.info(String.format("Проверяемое значение, тип пари Рассчитанные: %s", value));
        double actualValue = convertToDouble(value.contains("р.") ? value.replaceAll("р.", "")
                .trim() : value);
        return actualValue >= 0;
    }

    private boolean isWaitingForCalculation(SelenideElement element) {
        SelenideElement innerElement = element.find(By.xpath(".//span"));
        if (innerElement.has(attribute("title"))) {
            return innerElement.getAttribute("title")
                    .equals("Пари ещё не расчитано");
        }
        return false;
    }

    /**
     * Получить полную дату ставки время + дата
     * Тк в столбце с временем ставки не отображается год, то полную дату получаем путем конкатенации времени
     * ставки из столбца со временем, а дату берем из заголовка ставок на каждый отдельный день
     *
     * @param betTime элемент, содержащий время ставки
     * @return полная дата ставки
     */
    private String getBetFullDate(SelenideElement betTime) {
        By dateHeaderLocator = By.xpath("./parent::div/parent::div/preceding-sibling::div[contains(@class,'date-header')][1]");
        return format("%s %s", Objects.requireNonNull(betTime.getText())
                .split(",")[0], betTime.find(dateHeaderLocator)
                .getText());
    }

    /**
     * Get stake from table by it's barcode
     *
     * @param barcode stake's barcode
     * @return {@link StakeBlock} object, represents one stake in table
     */
    public StakeBlock getStake(String barcode) {
        return new StakeBlock(barcode);
    }

    /**
     * Get stake from table by index starting from 1
     *
     * @param index - stake position in table. Starts from 1
     * @return new StakeBlock object
     */
    public StakeBlock getStake(int index) {
        return new StakeBlock($$("[id*=stake__block]").get(index));
    }
}
