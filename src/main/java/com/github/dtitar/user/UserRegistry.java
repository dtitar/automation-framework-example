package com.github.dtitar.user;

import java.util.concurrent.atomic.AtomicInteger;

import static com.github.dtitar.user.UserFactory.createRegisteredUser;

public class UserRegistry {
    private static AtomicInteger COUNTER = new AtomicInteger(0);

    public static User getTestUser() {
        int index = COUNTER.incrementAndGet();
        return createRegisteredUser(index);
    }
}
