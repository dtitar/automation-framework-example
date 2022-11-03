package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.dtitar.pages.Pages;
import com.github.dtitar.steps.DbSteps;
import com.github.dtitar.steps.EventSteps;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.github.dtitar.config.ConfigHelper.getAppConfig;
import static com.github.dtitar.core.driver.DriverSettings.configureDriver;
import static com.github.dtitar.core.driver.DriverSettings.isVideoOn;
import static com.github.dtitar.core.driver.DriverUtils.getSessionId;
import static com.github.dtitar.core.helpers.AllureAttachments.addBrowserConsoleLogs;
import static com.github.dtitar.core.helpers.AllureAttachments.addScreenshotAs;
import static com.github.dtitar.core.helpers.AllureAttachments.addVideo;

@ExtendWith({AllureJunit5.class})
public class BaseTest {
    protected Pages pages;
    protected DbSteps dbSteps;
    protected EventSteps eventSteps;

    @BeforeAll
    static void setupDriver() {
        configureDriver();
    }

    @BeforeEach
    public void setupTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.baseUrl = getAppConfig().startUrl();
        pages = new Pages();
        dbSteps = new DbSteps();
        eventSteps = new EventSteps();
    }

    @AfterEach
    public void addAttachments() {
        addScreenshotAs("Last screenshot");
        addBrowserConsoleLogs();
        String sessionId = getSessionId();
        closeWebDriver();
        if (isVideoOn()) {
            addVideo(sessionId);
        }
    }
}