package com.github.dtitar.amplitude.assertions;

import com.github.dtitar.amplitude.model.user_activity.EventsItem;
import com.github.dtitar.amplitude.model.user_activity.UserData;

/**
 * Entry point for different amplitude custom assertions
 */
public class AmplitudeAssertions {

    public static EventsItemAssert assertThat(EventsItem actual) {
        return new EventsItemAssert(actual);
    }

    public static UserDataAssert assertThat(UserData actual) {
        return new UserDataAssert(actual);
    }
}
