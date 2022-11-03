package com.github.dtitar.amplitude;

import com.github.dtitar.amplitude.model.user_activity.EventsItem;
import com.github.dtitar.amplitude.model.user_activity.UserActivity;
import com.github.dtitar.amplitude.model.user_search.UserSearch;
import com.github.dtitar.api.spec.AmplitudeDashboardApiSpec;
import com.github.dtitar.user.User;
import io.qameta.allure.Step;


import java.util.List;
import java.util.Objects;

import static com.github.dtitar.api.Endpoint.AMPLITUDE_USER_ACTIVITY;
import static com.github.dtitar.api.Endpoint.AMPLITUDE_USER_SEARCH;
import static io.restassured.RestAssured.given;


public class AmplitudeSteps {

    @Step("Получаем список событий амплитуды пользователя")
    public static UserActivity getUserActivity(User user) {
        UserSearch userSearch = getUserSearch(user.getClientNumber());
        long userAmplitudeId = userSearch.getMatches()
                .get(0)
                .getAmplitudeId();

        return given().spec(new AmplitudeDashboardApiSpec().getRequest())
                .queryParam("user", userAmplitudeId)
                .when()
                .get(AMPLITUDE_USER_ACTIVITY.getValue())
                .then()
                .extract()
                .as(UserActivity.class);
    }

    @Step("Отправляем запрос getUserSearch в Amplitude Dashboard api для пользователя с clientNumber = {0}")
    public static UserSearch getUserSearch(int clientNumber) {
        return given().spec(new AmplitudeDashboardApiSpec().getRequest())
                .queryParam("user", String.valueOf(clientNumber))
                .when()
                .get(AMPLITUDE_USER_SEARCH.getValue())
                .then()
                .extract()
                .as(UserSearch.class);
    }

    @Step("Получаем Amplitude Event с eventType: {1}")
    public static EventsItem getEventsItemByEventType(List<EventsItem> events, String eventType) {
        return events.stream()
                .filter(e -> e.getEventType()
                        .equals(eventType))
                .findFirst()
                .orElse(null);
    }
}
