package com.github.dtitar.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.dtitar.config.ConfigHelper;
import com.github.dtitar.core.exceptions.AutoTestError;
import com.github.dtitar.pages.elements.Footer;
import com.github.dtitar.pages.elements.Header;
import com.github.dtitar.pages.elements.RightSideBar;
import com.github.dtitar.pages.elements.mybets.MyBetsWidget;
import com.github.dtitar.steps.UserSteps;
import com.github.dtitar.user.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;
import static org.openqa.selenium.By.className;

@Slf4j
@Getter
public abstract class BasePage {

    public static final String LIGA_STAVOK_URL = ConfigHelper.getAppConfig()
            .startUrl();
    public static final SelenideElement LOADER = $x("//*[local-name()='circle' and contains(@class, 'loader-circle') or @class='loader-frame' or @data-pending='true']");

    private Header header;
    private Footer footer;
    private RightSideBar rightSideBar;
    private MyBetsWidget myBetsWidget;

    public BasePage() {
        this.header = page(Header.class);
        this.footer = page(Footer.class);
        this.rightSideBar = page(RightSideBar.class);
        this.myBetsWidget = page(MyBetsWidget.class);
    }

    public void closeCookieWarningMessage() {
        final SelenideElement cookiesWarningMessage = $(className("application__cookies-warning-040f5d"));
        final SelenideElement acceptCookiesBtn = $("a[class*=application__cookies-warning-button]");

        if (cookiesWarningMessage.isDisplayed()) {
            acceptCookiesBtn.shouldBe(visible)
                    .click();
            Wait().withMessage("Ожидание исчезновения сообщения о необходимости принятия cookies")
                    .until(ExpectedConditions.invisibilityOf(cookiesWarningMessage));
        }
    }

    /**
     * Authorization user with cookie
     * @param user
     * @return
     */
    public static MainPage openAuthorized(User user) {
        UserSteps userSteps = new UserSteps();
        String token = userSteps.getAuthToken(user);
        open("/favicon.ico");
        WebDriverRunner.getWebDriver()
                .manage()
                .addCookie(new Cookie.Builder("token", token).expiresOn(new Date(Long.parseLong("2147483647000")))
                        .domain(getDomainName(LIGA_STAVOK_URL))
                        .path("/")
                        .build());
        log.info("Cписок передаваемых cookie:\n {}", getWebDriver()
                .manage()
                .getCookies());
        open("");
        return page(MainPage.class);
    }

    public static String getDomainName(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            throw new AutoTestError(format("Cannot get domain for URL: %s", url));
        }
    }
}
