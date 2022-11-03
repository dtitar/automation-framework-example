package com.github.dtitar.amplitude.assertions;

import com.github.dtitar.amplitude.model.user_activity.UserData;
import org.assertj.core.api.AbstractAssert;

public class UserDataAssert extends AbstractAssert<UserDataAssert, UserData> {
    protected UserDataAssert(UserData actual) {
        super(actual, UserDataAssert.class);
    }

    public UserDataAssert hasUserId(int userId) {
        isNotNull();
        if (actual.getUserId() != userId) {
            failWithMessage("Expected userId to be <%s> but was <%s>", userId, actual.getUserId());
        }
        return this;
    }
}
