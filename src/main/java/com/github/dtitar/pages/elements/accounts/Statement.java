package com.github.dtitar.pages.elements.accounts;

import com.codeborne.selenide.SelenideElement;
import com.github.dtitar.core.helpers.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

/**
 * Class represents statement in the table with statements
 */
@AllArgsConstructor
@Getter
public final class Statement {

    private static final DateTimeFormatter STATEMENT_DATE_FORMATTER = DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy HH:mm:ss")
            .withLocale(new Locale("ru"));

    private static final By DATE_HEADER = cssSelector("header[class*=statement__date]");
    private static final By TIME = xpath(".//*[contains(@class, 'statement-item__block') and text()][1]");
    private static final By TYPE = cssSelector("[class*=statement-item__type]");
    private static final By AMOUNT = cssSelector("[class*=statement-item__sum-info]");
    private static final By TOTAL = xpath(".//*[contains(@class, 'statement-item__block') and text()][2]");
    private static final By STATUS = cssSelector("[class*=statement-item__status]");
    private static final By ID = cssSelector("[class*=statement-item__id]");

    private final By VERIFY_PAYMENT_BTN = cssSelector("button[class*=statement-item__cupis]");
    private final By DECLINE_PAYMENT_BTN = cssSelector("[class*=statement-item__cancel-withdrawal");

    private SelenideElement statement;
    private LocalDateTime operationTime;
    private String operationType;
    private BigDecimal amount;
    private BigDecimal tax;
    private BigDecimal total;
    private String status;
    private String operationNumber;

    public Statement(SelenideElement statement) {
        this.statement = statement;
        this.operationTime = makeFullOperationDate();
        this.operationType = statement.find(TYPE)
                .getText();
        this.amount = BigDecimal.valueOf(Utils.convertToDouble(statement.find(AMOUNT)
                .getText()));
        //this.tax="";
        this.total = BigDecimal.valueOf(Utils.convertToDouble(statement.find(TOTAL)
                .getText()));
        this.operationNumber = statement.find(ID)
                .getText();
    }

    public Statement(int operationNumber) {
        this($x(format("//*[contains(@class,'statement-item__id') and text()='%s']/ancestor::*[contains(@class,'statement-item-')]",
                operationNumber)));
    }


    public SelenideElement getVerifyPaymentBtn() {
        return statement.find(VERIFY_PAYMENT_BTN);
    }

    public SelenideElement getDeclinePaymentBtn() {
        return statement.find(DECLINE_PAYMENT_BTN);
    }

    public String getStatus() {
        if (getDeclinePaymentBtn().is(not(exist))) {
            this.status = statement.find(STATUS)
                    .getText();
        }
        return status;
    }

    private LocalDateTime makeFullOperationDate() {
        return LocalDateTime.parse(format("%s %s", statement.find(xpath("./preceding-sibling::header"))
                        .getText()
                        .split(",")[0],
                statement.find(TIME)
                        .getText()
                        .split(", ")[1]), STATEMENT_DATE_FORMATTER);
    }
}

