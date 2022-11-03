package com.github.dtitar.user;

import com.github.dtitar.config.user.UserConfig;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.github.dtitar.core.helpers.DateUtils.getCurrentDateMinusYears;

@Slf4j
public class UserFactory {
    private static final Faker RANDOM = new Faker(new Locale("ru"));
    private static final String USER_DATA_REGEX = "[а-яА-Я]{8,10}";
    private static final String DEFAULT_PASSWORD = "123123eE";
    private static final String BIRTHDATE_PATTERN = "dd-MM-yyyy";

    @Step("Подготавливаем данные рандомного пользователя")
    public static User createRandomUser() {

        User user = User.builder()
                .email(String.format("%s_%s_%s@autotest.none", System.getProperty("tribe"), System.getProperty("squad"), RANDOM.regexify("[a-zA-Z]{10}")))
                .lastName(RANDOM.name()
                        .lastName())
                .firstName(RANDOM.name()
                        .firstName())
                .middleName(RANDOM.name()
                        .firstName())
                .birthDate(getCurrentDateMinusYears(BIRTHDATE_PATTERN, 18))
                .mobilePhone("79" + RANDOM.phoneNumber()
                        .subscriberNumber(9))
                .offerAccepted(true)
                .city(RANDOM.address()
                        .city())
                .password(DEFAULT_PASSWORD)
                .cupisPassword(DEFAULT_PASSWORD)
                .passportId(RANDOM.number()
                        .digits(10))
                .subDivisionCode(RANDOM.number()
                        .digits(6))
                .passportIsIssuedBy(RANDOM.regexify(USER_DATA_REGEX))
                .birthLocation(RANDOM.regexify(USER_DATA_REGEX))
                .countryCode("RU")
                .citizenshipCountry("Россия")
                .build();
        log.info(user.toString());
        return user;
    }

    public static User createRegisteredUser() {
        return UserRegistry.getTestUser();
    }

    public static User createRegisteredUser(int userIndex) {

        Map<String, String> registeredUserIndex = new HashMap<>();
        registeredUserIndex.put("index", String.valueOf(userIndex));
        UserConfig userConfig = ConfigFactory.create(UserConfig.class, registeredUserIndex);
        return User.builder()
                .email(userConfig.email())
                .lastName(userConfig.lastName())
                .firstName(userConfig.firstName())
                .middleName(userConfig.middleName())
                .birthDate(userConfig.birthDate())
                .mobilePhone(userConfig.mobilePhone())
                .city(userConfig.city())
                .password(userConfig.password())
                .cupisPassword(userConfig.passwordCupis())
                .build();
    }
}