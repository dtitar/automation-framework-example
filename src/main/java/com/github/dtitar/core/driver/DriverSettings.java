package com.github.dtitar.core.driver;

import com.codeborne.selenide.Configuration;
import com.github.dtitar.config.ConfigHelper;
import com.github.dtitar.config.DriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Slf4j
public class DriverSettings {

    private static final String DEFAULT_TIMEZONE_NAME = "Europe/Moscow";
    private static final DriverConfig driverConfig = ConfigHelper.getDriverConfig();

    public static void configureDriver() {
        Configuration.browser = driverConfig.browser();
        Configuration.browserVersion = driverConfig.browserVersion();
        Configuration.browserSize = driverConfig.browserSize();
        Configuration.timeout = 10000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> chromePreferences = new HashMap<>();
        Map<String, Object> selenoidOptions = new HashMap<>();

        chromePreferences.put("profile.password_manager_enabled", false);
        chromePreferences.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", chromePreferences);

        chromeOptions.addArguments(
                "--disable-features=NetworkService",
                "--no-sandbox",
                "--disable-infobars",
                "--disable-popup-blocking",
                "--dns-prefetch-disable",
                "--disable-extensions",
                "--no-default-browser-check",
                "--disable-notifications");

        if (isRemoteDriver()) {
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);
            selenoidOptions.put("env", new String[]{"LC_ALL=ru_RU.UTF-8"});
            String timezone =
                    System.getProperty("timezone") == null ? DEFAULT_TIMEZONE_NAME : System.getProperty("timezone");
            selenoidOptions.put("timeZone", timezone);
            capabilities.setCapability("selenoid:options", selenoidOptions);
            Configuration.remote = getRemoteDriverUrl();
        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
    }

    public static boolean isRemoteDriver() {
        return Boolean.parseBoolean(driverConfig.remoteDriver());
    }

    public static String getRemoteDriverUrl() {
        return format("http://%s:%s/wd/hub", driverConfig.remoteIP(),
                driverConfig.remotePort());
    }

    public static boolean isVideoOn() {
        return !driverConfig.videoStorage()
                .equals("");
    }
}

