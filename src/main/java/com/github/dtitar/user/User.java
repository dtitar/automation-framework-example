package com.github.dtitar.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class User {

    private String mobilePhone;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String countryCode;
    private String countryOfLiving;
    private String citizenshipCountry;
    boolean offerAccepted;
    private String city;
    private String cupisPassword;
    private String cardNumber;
    private String passportId;
    private String subDivisionCode;
    private String passportIsIssuedBy;
    private String passportIssueDate;
    private String birthLocation;
    private int verificationId;
    private String authToken;
    private int clientId;
    private int clientNumber;

    // Рандомный набор не пройдет. Проверка идет на стороне DaData предлагаю использовать статичный адрес.
    @Builder.Default
    private String address = "Тверская обл, г Тверь, ул Малые Перемерки, д 3, кв 2";


    /**
     * Получаем мобильный телефон без 79 (код России) для заполнения полей на сайте, где 79 уже указаны в в форме ввода.
     *
     * @return обработанный номер телефона
     */
    public String getMobilePhoneForUi() {
        return mobilePhone.substring(2);
    }

    public String getPassportIssueDate() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getPassportSeries() {
        return passportId.substring(0, 4);
    }

    public String getPassportNumber() {
        return passportId.substring(4);
    }

    public String getPassportIssueDateUprid() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getBirthDateEN() {
        if (this.getBirthDate()
                .matches("\\d{2}-\\d{2}-\\d{4}")) {
            LocalDate localDate = LocalDate.parse(this.getBirthDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return getBirthDate();
    }

    public String getFullName() {
        return String.format("%s %s %s", lastName, firstName, middleName);
    }
}