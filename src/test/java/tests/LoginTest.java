package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Story("Авторизация")
@DisplayName("Авторизация пользователя")
public class LoginTest extends BaseTest {

    @Test
    @Tag("ui")
    @Tag("login")
    @Owner("dtitar")
    @DisplayName("Проверка элементов формы авторизации")
    void testLoginFormFields() {

        step("Открываем страницу авторизации", () -> {
            open("");
            pages.mainPage()
                    .getHeader()
                    .getLoginButton()
                    .click();
            pages.loginPage()
                    .getLoginForm()
                    .shouldBe(visible);
        });

        step("Проверяем форму авторизации по номеру телефона", () -> {
            pages.loginPage()
                    .getLoginWithMobilePhoneTab()
                    .shouldBe(enabled);
            pages.loginPage()
                    .getMobilePhoneField()
                    .shouldBe(visible);
            pages.loginPage()
                    .getPasswordField()
                    .shouldBe(visible);
            pages.loginPage()
                    .getSubmitButton()
                    .shouldBe(disabled);
        });

        step("Нажимаем по вкладке E-mail", () -> {
            pages.loginPage()
                    .getLoginWithEmailTab()
                    .click();
            pages.loginPage()
                    .getEmailField()
                    .shouldBe(visible);
        });

        step("Проверяем форму авторизации по email", () -> {
            pages.loginPage()
                    .getLoginWithEmailTab()
                    .shouldBe(enabled);
            pages.loginPage()
                    .getEmailField()
                    .shouldBe(visible);
            pages.loginPage()
                    .getPasswordField()
                    .shouldBe(visible);
            pages.loginPage()
                    .getSubmitButton()
                    .shouldBe(disabled);
        });
    }

    @Test
    @Tag("ui")
    @Owner("dtitar")
    @DisplayName("Неудачная попытка логина по номеру телефона при указании некорректных данных")
    void testUserLoginWithMobilePhoneFail() {
        Faker fake = new Faker();
        String expectedLoginErrorMessageText = "Неверно указан номер телефона или пароль";

        step("Открываем страницу авторизации", () -> {
            open("");
            pages.mainPage()
                    .getHeader()
                    .getLoginButton()
                    .click();
            pages.loginPage()
                    .getLoginForm()
                    .shouldBe(visible);
        });

        step("Заполняем форму авторизации по номеру телефона некорректными данными", () -> {
            pages.loginPage()
                    .getMobilePhoneField()
                    .setValue(fake.phoneNumber()
                            .subscriberNumber(10));
            pages.loginPage()
                    .getPasswordField()
                    .setValue(fake.internet()
                            .password());
            pages.loginPage()
                    .getSubmitButton()
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверяем отображение ошибки авторизации", () -> {
            pages.loginPage()
                    .getLoginForm()
                    .shouldBe(visible);
            pages.loginPage()
                    .getAuthFormErrorNotification()
                    .shouldHave(text(expectedLoginErrorMessageText));
        });
    }

    @Test
    @Tag("ui")
    @Owner("dtitar")
    @DisplayName("Неудачная попытка логина по email при указании некорректных данных")
    void testUserLoginWithEmailFail() {
        Faker fake = new Faker();
        String expectedLoginErrorMessageText = "Неверно указан E-mail или пароль";

        step("Открываем страницу авторизации", () -> {
            open("");
            pages.mainPage()
                    .getHeader()
                    .getLoginButton()
                    .click();
            pages.loginPage()
                    .getLoginForm()
                    .shouldBe(visible);
        });

        step("Заполняем форму авторизации по email некорректными данными", () -> {
            pages.loginPage()
                    .getLoginWithEmailTab()
                    .click();
            pages.loginPage()
                    .getEmailField()
                    .setValue(fake.internet()
                            .emailAddress());
            pages.loginPage()
                    .getPasswordField()
                    .setValue(fake.internet()
                            .password());
            pages.loginPage()
                    .getSubmitButton()
                    .shouldBe(enabled)
                    .click();
        });

        step("Проверяем отображение ошибки авторизации", () -> {
            pages.loginPage()
                    .getLoginForm()
                    .shouldBe(visible);
            pages.loginPage()
                    .getAuthFormErrorNotification()
                    .shouldHave(text(expectedLoginErrorMessageText));
        });
    }
}
