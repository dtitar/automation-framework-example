package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Личный кабинет")
@DisplayName("Сброс пароля пользователя")
class ResetPasswordTest extends BaseTest {

    @Test
    @Tag("ui")
    @Owner("dtitar")
    @DisplayName("Проверка формы сброса пароля")
    void testResetPasswordFormFields() {

        step("Открываем страницу авторизации", () -> {
            open("/login");
            pages.loginPage().getLoginForm().shouldBe(visible);
        });

        step("Нажимаем на ссылку сброса пароля", () -> {
            pages.loginPage().getResetPasswordLink().click();
            pages.resetPasswordPage().getResetPasswordHeader().shouldBe(visible);

        });

        step("Проверяем форму сброса пароля по номеру телефона", () -> {
            pages.resetPasswordPage().getResetPasswordWithPhoneTab().shouldBe(enabled);
            pages.resetPasswordPage().getMobilePhoneField().shouldBe(visible);
            pages.resetPasswordPage().getCodeBySmsButton()
                    .shouldBe(disabled)
                    .shouldHave(text("Получить код по SMS"));
        });

        step("Кликаем по вкладке Email", () -> {
            pages.resetPasswordPage().getResetPasswordWithEmailTab().click();
            pages.resetPasswordPage().getEmailField().shouldBe(visible);
        });

        step("Проверяем форму сброса пароля по email", () -> {
            pages.resetPasswordPage().getResetPasswordWithEmailTab().shouldBe(enabled);
            pages.resetPasswordPage().getEmailField().shouldBe(visible);
            pages.resetPasswordPage().getSendInstructionByEmailButton()
                    .shouldBe(disabled)
                    .shouldHave(text("Отправить инструкцию на e-mail"));
        });
    }

    @Test
    @Tag("ui")
    @Owner("dtitar")
    @DisplayName("Проверка отображения ошибки сброса пароля при указании некорректного номера телефона")
    void testResetPasswordFormWithMobilePhoneInvalidData() {
        Faker fake = new Faker();
        String expectedResetPasswordWarningMessageText = "Номер не найден. Проверьте правильность введённого номера телефона или укажите email";

        step("Открываем форму сброса пароля по номеру телефона", () -> {
            open("/ResetPassword");
            pages.resetPasswordPage().getResetPasswordHeader().shouldBe(visible);
        });

        step("Заполняем поле 'Номер телефона' и нажать кнопку 'Получить код по смс'", () -> {
            pages.resetPasswordPage().getMobilePhoneField().setValue(fake.phoneNumber().subscriberNumber(9));
            pages.resetPasswordPage().getCodeBySmsButton().shouldBe(enabled).click();
        });

        step("Проверяем, что отображается сообщение об ошибке", () -> {
            pages.resetPasswordPage().getResetPasswordWarning().shouldBe(visible)
                    .shouldHave(text(expectedResetPasswordWarningMessageText));
        });
    }

    @Test
    @Tag("ui")
    @Owner("dtitar")
    @DisplayName("Проверка отображения ошибки сброса пароля при указании некорректного номера телефона")
    void testResetPasswordFormWithEmailInvalidData() {
        Faker fake = new Faker();
        String expectedResetPasswordWarningMessageText = "Указанный электронный адрес не найден среди зарегистрированных в системе";

        step("Открываем форму сброса пароля по email", () -> {
            open("/ResetPassword");
            pages.resetPasswordPage().getResetPasswordHeader().shouldBe(visible);
            pages.resetPasswordPage().getResetPasswordWithEmailTab().click();
            pages.resetPasswordPage().getEmailField().shouldBe(visible);
        });

        step("Заполняем поле email и нажать на кнопку 'Отправить инструкцию по email'", () -> {
            pages.resetPasswordPage().getEmailField().setValue(fake.internet().emailAddress());
            pages.resetPasswordPage().getSendInstructionByEmailButton().shouldBe(enabled).click();
        });

        step("Проверяем отображение сообщения об ошибке смены пароля", () -> {
            pages.resetPasswordPage().getResetPasswordWarning().shouldBe(visible)
                    .shouldHave(text(expectedResetPasswordWarningMessageText));
        });
    }

    @Test
    @Tag("ui")
    @Owner("dtitar")
    @DisplayName("Проверка отображения ошибок при некорректном вводе данных в поля формы сброса пароля")
    @Disabled("требуется актуализация тестового сценария")
    void testResetPasswordFormFieldsErrorNotifications() {
        String expectedMobilePhoneInputErrorMessageText = "Это поле обязательно к заполнению";

        step("Открываем форму сброса пароля по мобильному телефону", () -> {
            open("/ResetPassword");
            pages.resetPasswordPage().getResetPasswordHeader().shouldBe(visible);
        });

        step("Кликаем по полю 'Мобильный телефон'", () -> {
            pages.resetPasswordPage().getMobilePhoneField().click();
            pages.resetPasswordPage().getCodeBySmsButton().click();
        });

        step("Проверяем отображение уведомления для поля 'Мобильный телефон'", () -> {
            pages.resetPasswordPage().getMobilePhoneInputErrorMessage().shouldBe(visible)
                    .shouldHave(text(expectedMobilePhoneInputErrorMessageText));
        });
    }
}
