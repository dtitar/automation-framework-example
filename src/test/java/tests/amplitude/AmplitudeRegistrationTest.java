package tests.amplitude;

import com.github.dtitar.amplitude.AmplitudeSteps;
import com.github.dtitar.amplitude.model.user_activity.EventProperties;
import com.github.dtitar.amplitude.model.user_activity.EventsItem;
import com.github.dtitar.amplitude.model.user_activity.UserActivity;
import com.github.dtitar.user.User;
import com.github.dtitar.user.UserFactory;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.open;
import static com.github.dtitar.amplitude.assertions.AmplitudeAssertions.assertThat;
import static com.github.dtitar.config.ConfigHelper.getAppConfig;
import static com.github.dtitar.pages.BasePage.getDomainName;
import static io.qameta.allure.Allure.step;
import static java.time.ZoneId.of;

public class AmplitudeRegistrationTest extends BaseTest {

    private User user;
    private String expectedHostName;
    private LocalDateTime testStartDateTimeInUtc;
    EventProperties expectedEventProperties;


    @BeforeEach
    void arrangeTestData() {
        user = UserFactory.createRandomUser();
        expectedHostName = getDomainName(getAppConfig().startUrl());
        expectedEventProperties = new EventProperties();
        expectedEventProperties.setHostname(expectedHostName);
        testStartDateTimeInUtc = LocalDateTime.now(of("UTC"));
    }

    @Tag("amplitude")
    @DisplayName("[Amplitude]. Registration start")
    @Test
    void shouldSendRegistrationEventsToAmplitude() {
        step("Шаг 1. Go to the main page",
                () -> {
                    open("");
                });

        EventsItem expectedRegistrationButtonClickStartRegistration = new EventsItem();
        step("Шаг 2. Нажать на кнопку [Зарегистрироваться]",
                () -> {
                    pages.mainPage()
                            .getHeader()
                            .getRegisterButton()
                            .click();

                    step("Подготавливаем эталонные данные для события Registration_ButtonClick_StartRegistration",
                            () -> {
                                expectedRegistrationButtonClickStartRegistration.setEventType("Registration_ButtonClick_StartRegistration");
                                expectedRegistrationButtonClickStartRegistration.setEventProperties(expectedEventProperties);
                                expectedRegistrationButtonClickStartRegistration.setEventTime(LocalDateTime.now(of("UTC"))
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
                            });
                });

        EventsItem expectedConversionStepPromoLandingClick = new EventsItem();
        step("Шаг 3. Ввести номер телефона. Нажать на кнопку [Зарегистрироваться]",
                () -> {
                    pages.wheelOfFortuneRegistrationPage()
                            .getMobilePhoneField()
                            .setValue(user.getMobilePhone()
                                    .substring(2));
                    pages.wheelOfFortuneRegistrationPage()
                            .clickRegisterButton();

                    step("Подготавливаем эталонные данные для события conversionStep_[promo_landing]_click",
                            () -> {
                                expectedConversionStepPromoLandingClick.setEventType("conversionStep_[promo_landing]_click");
                                EventProperties eventProperties = expectedEventProperties;
                                eventProperties.setLandingName("wheel_fortune");
                                expectedConversionStepPromoLandingClick.setEventProperties(eventProperties);
                            });
                });

        EventsItem expectedRegistrationPageViewPhone = new EventsItem();
        step("Шаг 4. Достать код из базы и ввести его на странице",
                () -> {
                    pages.wheelOfFortuneRegistrationPage()
                            .getConfirmMobilePhonePopup()
                            .getCodeField()
                            .setValue(String.valueOf(dbSteps.getSmsIdentificationCode(user.getMobilePhone())));
                    pages.wheelOfFortuneRegistrationPage()
                            .getConfirmMobilePhonePopup()
                            .getSendButton()
                            .shouldBe(enabled)
                            .click();

                    step("Подготавливаем эталонные данные для события Registration_PageView_Phone",
                            () -> {
                                expectedRegistrationPageViewPhone.setEventType("Registration_PageView_Phone");
                                expectedRegistrationPageViewPhone.setEventProperties(expectedEventProperties);
                            });
                });

        EventsItem expectedRegistrationInputBirthDate = new EventsItem();
        step("Шаг 5. Вводим дату рождения",
                () -> {
                    pages.registrationPage()
                            .getBirthdayField()
                            .setValue(user.getBirthDate());

                    step("Подготавливаем эталонные данные для события Registration_Input_BirthDate",
                            () -> {
                                expectedRegistrationInputBirthDate.setEventType("Registration_Input_BirthDate");
                                expectedRegistrationInputBirthDate.setEventProperties(expectedEventProperties);
                            });
                });

        step("Шаг 6. Вводим страну", () -> {
            pages.registrationPage()
                    .setCitizenship("Россия");
        });

        EventsItem expectedRegistrationInputPassword = new EventsItem();
        step("Шаг 7. Вводим пароль",
                () -> {
                    pages.registrationPage()
                            .getPasswordField()
                            .setValue(user.getPassword());

                    step("Подготавливаем эталонные данные для события Registration_Input_Password",
                            () -> {
                                expectedRegistrationInputPassword.setEventType("Registration_Input_Password");
                                expectedRegistrationInputPassword.setEventProperties(expectedEventProperties);
                            });
                });
        EventsItem expectedRegistrationButtonClickRegistrationON = new EventsItem();
        step("Шаг 8. Нажимаем [Зарегистрироваться]",
                () -> {
                    pages.registrationPage()
                            .getRegisterButton()
                            .shouldBe(enabled)
                            .click();
                    pages.simpleFormPage()
                            .waitForDataToBeSent();
                    pages.shopsPage();


                    step("Подготавливаем эталонные данные для события Registration_ButtonClick_RegistrationON",
                            () -> {
                                expectedRegistrationButtonClickRegistrationON.setEventType("Registration_ButtonClick_RegistrationON");
                                expectedRegistrationButtonClickRegistrationON.setEventProperties(expectedEventProperties);
                            });
                });

        step("Шаг 9-12. Сравниваем события, полученные из Амплитуды, с ожидаемыми",
                () -> {
                    int clientNumber = dbSteps.getClientNumber(user.getMobilePhone());
                    user.setClientNumber(clientNumber);
                    UserActivity activity = AmplitudeSteps.getUserActivity(user);
                    assertThat(activity.getUserData()).hasUserId(clientNumber);
                    assertThat(activity.getEvent("Registration_ButtonClick_StartRegistration"))
                            .hasEventType(expectedRegistrationButtonClickStartRegistration.getEventType())
                            .hasHostNameEventProperty(expectedHostName)
                            .isEventTimeBetween(testStartDateTimeInUtc, expectedRegistrationButtonClickStartRegistration.getEventTimeDefault());

                    assertThat(activity.getEvent("conversionStep_[promo_landing]_click"))
                            .hasEventType(expectedConversionStepPromoLandingClick.getEventType())
                            .hasHostNameEventProperty(expectedHostName)
                            .hasLandingNameEventProperty(expectedConversionStepPromoLandingClick.getEventProperties()
                                    .getLandingName());

                    assertThat(activity.getEvent("Registration_PageView_Phone"))
                            .hasEventType(expectedRegistrationPageViewPhone.getEventType())
                            .hasHostNameEventProperty(expectedHostName);

                    assertThat(activity.getEvent("Registration_Input_BirthDate"))
                            .hasEventType(expectedRegistrationInputBirthDate.getEventType())
                            .hasHostNameEventProperty(expectedHostName);

                    assertThat(activity.getEvent("Registration_Input_Password"))
                            .hasEventType(expectedRegistrationInputPassword.getEventType())
                            .hasHostNameEventProperty(expectedHostName);

                    assertThat(activity.getEvent("Registration_ButtonClick_RegistrationON"))
                            .hasEventType(expectedRegistrationButtonClickRegistrationON.getEventType())
                            .hasHostNameEventProperty(expectedHostName);
                });
    }
}

