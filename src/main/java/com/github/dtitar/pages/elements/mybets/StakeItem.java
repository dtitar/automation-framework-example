package com.github.dtitar.pages.elements.mybets;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.openqa.selenium.By.xpath;

@AllArgsConstructor
@Getter
public class StakeItem {

    private static final DateTimeFormatter EVENT_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy HH:mm");
    private static final By INFO_MARKET_AND_TITLE = xpath("//a[contains(@class, 'stake__link')]");
    private static final By INFO_LINE_NUMBER_AND_DATE = xpath("//*[contains(@class, 'stake__event-info-') and contains(text(), '№')]");
    private static final By INFO_SPORT_AND_CATEGORY_AND_TOURNAMENT = xpath("//*[contains(@class, 'stake__event-info-')][last()]");

    private SelenideElement itemElement;
    private String market;
    private String title;
    private String lineNumber;
    private LocalDateTime startDate;
    private String sportName;
    private String categoryName;
    private String tournamentName;

    public StakeItem(SelenideElement itemElement) {
        this.itemElement = itemElement;
        this.market = itemElement.find(INFO_MARKET_AND_TITLE).getText().split("/")[0];
        this.title = itemElement.find(INFO_MARKET_AND_TITLE).getText().split("/")[1];
        this.lineNumber = itemElement.find(INFO_LINE_NUMBER_AND_DATE).getText().split("№")[1];
        this.startDate = LocalDateTime.parse(itemElement.find(INFO_LINE_NUMBER_AND_DATE).getText()
                .split(",")[1]
                .replace("в ", ""), EVENT_TIME_FORMATTER);

        String infoSportCategoryTournament = itemElement.find(INFO_SPORT_AND_CATEGORY_AND_TOURNAMENT).getText();
        this.sportName = infoSportCategoryTournament.split("\\.")[0];
        if (StringUtils.countMatches(infoSportCategoryTournament, ".") == 1) {
            this.tournamentName = infoSportCategoryTournament.split("\\.")[1];
        } else {
            this.categoryName = infoSportCategoryTournament.split("\\.")[1];
            this.tournamentName = infoSportCategoryTournament.split("\\.")[2];
        }
    }
}
